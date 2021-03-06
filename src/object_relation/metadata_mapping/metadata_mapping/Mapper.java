package object_relation.metadata_mapping.metadata_mapping;

import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public abstract class Mapper {
  protected Connection db;
  protected DataMap dataMap;

  public Mapper() {
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

  public DomainObject findObject(Long id) {
    if (UnitOfWork.getCurrent().isLoaded(id)) return UnitOfWork.getCurrent().getObject(id);
    DomainObject result = null;
    try {
      String sql = "SELECT " + dataMap.columnList() + " FROM " + dataMap.getTableName() + " WHERE ID = ?";
      PreparedStatement stmt = this.db.prepareStatement(sql);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = load(rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  protected DomainObject load(ResultSet rs) throws Exception {
    long id = rs.getLong("ID");
    if (UnitOfWork.getCurrent().isLoaded(id)) return UnitOfWork.getCurrent().getObject(id);
    DomainObject result = (DomainObject) dataMap.getDomainClass().getConstructor().newInstance();
    result.setId(id);
    UnitOfWork.getCurrent().registerClean(result);
    loadFields(rs, result);
    return result;
  }

  protected void loadFields(ResultSet rs, DomainObject result) throws SQLException {
    for (ColumnMap columnMap : dataMap.getColumnMaps()) {
      Object columnValue = rs.getObject(columnMap.getColumnName());
      columnMap.setField(result, columnValue);
    }
  }

  public void insert(DomainObject obj) {
    String sql = "INSERT INTO " + dataMap.getTableName() + " VALUES (?" + dataMap.insertList() + ")";
    try {
      PreparedStatement stmt = db.prepareStatement(sql);
      stmt.setLong(1, obj.getId());
      int argCount = 2;
      for (ColumnMap columnMap : dataMap.getColumnMaps()) {
        stmt.setObject(argCount++, columnMap.getValue(obj));
      }
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void update(DomainObject obj) {
    String sql = "UPDATE " + dataMap.getTableName() + dataMap.updateList() + " WHERE id = ?";
    try {
      PreparedStatement stmt = db.prepareStatement(sql);
      int argCount = 1;
      for (ColumnMap columnMap : dataMap.getColumnMaps()) {
        stmt.setObject(argCount++, columnMap.getValue(obj));
      }
      stmt.setLong(argCount, obj.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(DomainObject obj) {
    String sql = "DELETE FROM " + dataMap.getTableName() + " WHERE id = ?";
    try {
      PreparedStatement stmt = db.prepareStatement(sql);
      stmt.setLong(1, obj.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Set findObjectWhere (String whereClause) {
    String sql = "SELECT " + dataMap.columnList() + " FROM " + dataMap.getTableName() + " WHERE " + whereClause;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Set result = null;
    try {
      stmt = db.prepareStatement(sql);
      rs = stmt.executeQuery();
      result = loadAll(rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public Set loadAll(ResultSet rs) throws Exception {
    Set result = new HashSet();
    while (rs.next()) {
      result.add(load(rs));
    }
    return result;
  }

  // QueryObject code
  public DataMap getDataMap() {
    return dataMap;
  }
}
