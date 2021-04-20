package object_relation.structure.concrete_table_inheritance;

import object_relation.structure.identity_field.key_table.KeyGenerator;

import java.sql.*;
import java.util.Properties;

public abstract class AbstractPlayerMapper extends Mapper {
  protected DomainObject abstractFind(long id, String tablename) throws SQLException {
    DomainObject result = (DomainObject) loadedMap.get(id);
    if (result != null) return result;

    ResultSet rs = findRow(id, tablename);
    if (rs == null) return null;

    result = createDomainObject();
    result.setId(rs.getLong("id"));
    load(result, rs);
    return result;
  }

  abstract protected DomainObject createDomainObject();
  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    Player player = (Player) obj;
    player.setName(rs.getString("name"));
    player.setType(rs.getString("type"));
  }

  @Override
  protected void update(DomainObject obj) {
    try {
      PreparedStatement stmt = DB.prepareStatement(updateStatement());
      stmt.setLong(4, obj.getId());
      save(obj, stmt);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void save(DomainObject obj, PreparedStatement stmt) throws SQLException {
    Player player = (Player) obj;
    stmt.setString(1, player.getName());
    stmt.setString(2, player.getType());
  }

  abstract protected String updateStatement();

  @Override
  protected void insert(DomainObject obj) {
    try {
      PreparedStatement stmt = DB.prepareStatement(insertStatement());
      KeyGenerator keyGenerator = new KeyGenerator(getNewConn(), "players", 1);
      stmt.setLong(4, keyGenerator.nextKey());
      save(obj, stmt);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private Connection getNewConn() {
    Connection db = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      db = DriverManager.getConnection(url, props);
    } catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }
    return db;
  }

  abstract protected String insertStatement();
}
