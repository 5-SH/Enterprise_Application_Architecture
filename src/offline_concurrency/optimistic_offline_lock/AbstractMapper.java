package offline_concurrency.optimistic_offline_lock;

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
          String modifiedBy = rs.getString(colmuns.length + 3);
          Timestamp modified = rs.getTimestamp(colmuns.length + 4);
          int version = rs.getInt(colmuns.length + 5);
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
  public void insert(DomainObject object) {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    object.setSystemFields(now, "admin", 0);
    AppSessionManager.getSession().getIdentityMap().put(object.getId(), object);
    try {
      PreparedStatement stmt = db.prepareStatement(insertSQL);
      stmt.setLong(1, object.getId().longValue());
      doInsert(object, stmt);
      stmt.setString(3, "admin");
      stmt.setTimestamp(4, now);
      stmt.setString(5, object.getModifiedBy());
      stmt.setTimestamp(6, object.getModified());
      stmt.setInt(7, object.getVersion());
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  abstract protected void doInsert(DomainObject object, PreparedStatement stmt) throws Exception;

  // update
  public void update(DomainObject object) {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    int curVersion = object.getVersion();
    int newVersion = curVersion + 1;
    try {
      PreparedStatement stmt = db.prepareStatement(updateSQL);
      stmt.setLong(1, object.getId().longValue());
      doUpdate(object, stmt);
      stmt.setString(3, "admin");
      stmt.setTimestamp(4, now);
      stmt.setInt(5, newVersion);
      stmt.setInt(6, curVersion);
      int rowCount = stmt.executeUpdate();
      if (rowCount == 0) {
        throwConcurrencyException(object);
      } else {
        object.setSystemFields(now, "admin", newVersion);
        AppSessionManager.getSession().getIdentityMap().put(object.getId(), object);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  abstract protected void doUpdate(DomainObject object, PreparedStatement stmt) throws Exception;

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
    for (int i = 0; i < columnsLength + 5; i++) {
      buffer.append("?, ");
    }
    buffer.setLength(buffer.length() - 2);
    this.insertSQL += (buffer.toString() + ");");

    this.updateSQL = "UPDATE " + this.table + " SET ";
    buffer = new StringBuffer();
    for (int i = 0; i < columnsLength; i++) {
      buffer.append(this.colmuns[i] + " = ?,");
    }
    buffer.append("modifiedby = ?,");
    buffer.append("modified = ?,");
    buffer.append("VERSION = ? ");
    buffer.append("WHERE version = ?");
    buffer.setLength(buffer.length());
    this.updateSQL += (buffer.toString() + ";");
  }
}
