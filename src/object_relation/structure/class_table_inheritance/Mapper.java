package object_relation.structure.class_table_inheritance;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class Mapper {
  protected Map loadedMap = new HashMap();
  protected Connection DB;

  public Mapper() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      this.DB = DriverManager.getConnection(url, props);
    } catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }
  }

  public ResultSet findRow(long id) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = DB.prepareStatement("SELECT type FROM athlete WHERE id = ?");
      stmt.setLong(1, id);
      rs = stmt.executeQuery();
      rs.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rs;
  }

  abstract protected DomainObject find(long id) throws SQLException;

  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    try {
      long id = rs.getLong("id");
      obj.setId(id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  abstract protected void update(DomainObject obj) throws SQLException;
}
