package object_relation.metadata_mapping.metadata_mapping;

import java.sql.*;
import java.util.Properties;

public abstract class Mapper {
  private Connection db;
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
    try {
      PreparedStatement stmt = db.prepareStatement(insertStatement());
      stmt.setLong(1, obj.getId());
      doInsert(obj, stmt);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected abstract String insertStatement();
  protected abstract void doInsert(DomainObject obj, PreparedStatement stmt) throws SQLException;

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
    try {
      PreparedStatement stmt = db.prepareStatement(deleteStatement());
      stmt.setLong(1, obj.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected abstract String deleteStatement();
}
