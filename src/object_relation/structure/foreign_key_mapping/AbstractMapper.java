package object_relation.structure.foreign_key_mapping;

import object_relation.structure.identity_field.compound_key.Key;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractMapper {
  protected Map loadedMap = new HashMap<Key, Object>();
  protected Connection DB;

  public AbstractMapper() {
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

  abstract protected String findStatement();

  protected DomainObject abstractFind(Long id) {
    DomainObject result = (DomainObject) loadedMap.get(id);
    if (result != null) return result;

    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = DB.prepareStatement(findStatement());
      stmt.setLong(1, id.longValue());
      rs = stmt.executeQuery();
      rs.next();
      result = load(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  protected DomainObject load(ResultSet rs) throws SQLException {
    Long id = new Long(rs.getLong(1));
    if (loadedMap.containsKey(id)) return (DomainObject) loadedMap.get(id);
    DomainObject result = doLoad(id, rs);
    doRegister(id, result);
    return result;
  }

  protected void doRegister(Long id, DomainObject result) {
    assert loadedMap.containsKey(id) : "loadedMap has key";
    loadedMap.put(id, result);
  }

  abstract protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException;

  abstract public void update(DomainObject arg);
}
