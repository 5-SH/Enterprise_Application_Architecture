package offline_concurrency.optimistic_offline_lock;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.text.DateFormat;
import java.util.Properties;

public abstract class AbstractMapper {
  private String table;
  private String[] colmuns;

  private String loadSQL;
  private String deleteSQL;
  private String insertSQL;
  private String updateSQL;

  protected Connection db;
  protected final String checkVersionSQL ="SELECT version, modifiedBy, modified FROM customer WHERE id = ?";

  public AbstractMapper(String table, String[] columns) {
    this.table = table;
    this.colmuns = columns;
    buildStatements();

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      this.db = DriverManager.getConnection(url, props);
    } catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }
  }

  public DomainObject find(Long id) {
    DomainObject obj = (DomainObject) AppSessionManager.getSession().getIdentityMap().get(id);
    if (obj == null) {
      try {
        PreparedStatement stmt = db.prepareStatement(loadSQL);
        stmt.setLong(1, id.longValue());
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
          obj = load(id, rs);
          String modifiedBy = rs.getString(colmuns.length + 2);
          Timestamp modified = rs.getTimestamp(colmuns.length + 3);
          int version = rs.getInt(colmuns.length + 4);
          obj.setSystemFields(modified, modifiedBy, version);
          AppSessionManager.getSession().getIdentityMap().put(id, obj);
        } else {
          throw new Exception(table + " " + id + " does not exist");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return obj;
  }

  protected abstract DomainObject load(Long id, ResultSet rs) throws SQLException;

  public void delete(DomainObject object) {
    AppSessionManager.getSession().getIdentityMap().remove(object.getId());
    try {
      PreparedStatement stmt = db.prepareStatement(deleteSQL);
      stmt.setLong(1, object.getId().longValue());
      stmt.setInt(2, object.getVersion());
      int rowCount = stmt.executeUpdate();
      if (rowCount == 0) {
        throwConcurrencyException(object);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // insert

  // update

  protected void throwConcurrencyException(DomainObject object) throws Exception {
    try {
      PreparedStatement stmt = db.prepareStatement(checkVersionSQL);
      stmt.setInt(1, (int) object.getId().longValue());
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        int version = rs.getInt(1);
        String modifiedBy = rs.getString(2);
        Timestamp modified = rs.getTimestamp(3);
        if (version > object.getVersion()) {
          String when = DateFormat.getDateInstance().format(modified);
          throw new Exception(table + " " + object.getId() + " modified by " + modifiedBy + " at " + when);
        } else if (version < object.getVersion()) {
          throw new Exception("unexpected error checking timestamp");
        }
      } else {
        throw new Exception(table + " " + object.getId() + " has been deleted");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void buildStatements() {
    int columnsLength = colmuns.length;

    this.loadSQL = "SELECT * FROM " + this.table + " WHERE id = ?";

    this.deleteSQL = "DELETE FROM " + this.table + " WHERE id =? and version = ?";

    this.insertSQL = "INSERT INTO " + this.table + " VALUES (";
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < columnsLength; i++) {
      buffer.append("?, ");
    }
    buffer.setLength(buffer.length() - 2);
    this.insertSQL += (buffer.toString() + ");");

    this.updateSQL = "UPDATE " + this.table + " SET ";
    buffer = new StringBuffer();
    for (int i = 0; i < columnsLength; i++) {
      buffer.append(this.colmuns[i] + " = ?,");
    }
    buffer.setLength(buffer.length() - 1);
    this.updateSQL += (buffer.toString() + ";");
  }
}
