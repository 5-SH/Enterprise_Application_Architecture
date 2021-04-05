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

  abstract protected DomainObject find(long id) throws SQLException;

  protected DomainObject abstractFind(long id) {
    DomainObject result = null;
    try {
      result = (DomainObject) loadedMap.get(id);
      if (result != null) return result;
      result = find(id);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    try {
      long id = rs.getLong("id");
      obj.setId(id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
