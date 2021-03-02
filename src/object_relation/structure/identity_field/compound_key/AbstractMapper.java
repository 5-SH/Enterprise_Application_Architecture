package object_relation.structure.identity_field.compound_key;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractMapper {
  abstract protected String findStatementString();
  protected Map loadedMap = new HashMap();
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

  protected abstract DomainObjectWithKey load(ResultSet rs);

  protected void loadFindStatement(Key key, PreparedStatement finder) throws SQLException {
    finder.setLong(1, key.longValue());
  }
}
