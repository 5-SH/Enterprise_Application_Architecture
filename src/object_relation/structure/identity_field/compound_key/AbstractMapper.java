package object_relation.structure.identity_field.compound_key;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractMapper {
  abstract protected String findStatementString();
  protected Map<Key, Object> loadedMap = new HashMap<Key, Object>();
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

  public DomainObjectWithKey abstractFind(Key key) {
    DomainObjectWithKey result = (DomainObjectWithKey) loadedMap.get(key);

    System.out.println("abstractFind method loadedMap contains?: " + (result != null));

    if (result != null) return result;

    ResultSet rs = null;
    PreparedStatement findStatement = null;
    try {
      findStatement = DB.prepareStatement(findStatementString());
      loadFindStatement(key, findStatement);
      rs = findStatement.executeQuery();
      rs.next();
      if (rs.isAfterLast()) return null;
      result = load(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  protected void loadFindStatement(Key key, PreparedStatement finder) throws SQLException {
    finder.setLong(1, key.longValue());
  }

  protected DomainObjectWithKey load(ResultSet rs) throws SQLException {
    Key key = createKey(rs);

    System.out.println("load method loadedMap contains?: " + loadedMap.containsKey(key));

    if (loadedMap.containsKey(key)) return (DomainObjectWithKey) loadedMap.get(key);
    DomainObjectWithKey result = doLoad(key, rs);
    loadedMap.put(key, result);
    return result;
  }

  abstract protected DomainObjectWithKey doLoad(Key key, ResultSet rs) throws SQLException;

  protected Key createKey(ResultSet rs) throws SQLException {
    return new Key(rs.getLong(1));
  }

  public Key insert(DomainObjectWithKey subject) {
    Key key = null;
    try {
      key = performInsert(subject, findNextDatabaseKeyObject());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return key;
  }

  abstract protected Key findNextDatabaseKeyObject() throws SQLException;

  protected Key performInsert(DomainObjectWithKey subject, Key key) throws SQLException {
    subject.setKey(key);
    PreparedStatement stmt = DB.prepareStatement(insertStatementString());
    insertKey(subject, stmt);
    insertData(subject, stmt);
    stmt.execute();
    loadedMap.put(subject.getKey(), subject);

    return subject.getKey();
  }

  abstract protected String insertStatementString();

  protected  void insertKey(DomainObjectWithKey subject, PreparedStatement stmt) throws SQLException {
    stmt.setLong(1, subject.getKey().longValue());
  }

  abstract protected void insertData(DomainObjectWithKey subject, PreparedStatement stmt) throws SQLException;
}
