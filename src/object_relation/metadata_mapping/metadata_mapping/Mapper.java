package object_relation.metadata_mapping.metadata_mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class Mapper {
  private Map<Long, DomainObject> loadedMap = new HashMap<>();
  private Connection db;

  public void setConnection(Connection db) { this.db = db; }

  public DomainObject abstractFind(Long id) {
    DomainObject result = (DomainObject) loadedMap.get(id);
    if (result != null) return result;
    try {
      PreparedStatement stmt = db.prepareStatement(findStatement());
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = load(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  protected abstract String findStatement();
  protected DomainObject load(ResultSet rs) throws SQLException {
    Long id = new Long(rs.getLong(1));
    if (loadedMap.containsKey(id)) return loadedMap.get(id);
    DomainObject result = doLoad(id, rs);
    loadedMap.put(id, result);
    return result;
  }

  protected abstract DomainObject doLoad(Long id, ResultSet rs) throws SQLException;

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
    try {
      PreparedStatement stmt = db.prepareStatement(updateStatement());
      doUpdate(obj, stmt);
      stmt.executeUpdate();

      if (!loadedMap.containsKey(obj.getId())) loadedMap.put(obj.getId(), obj);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected abstract String updateStatement();
  protected abstract void doUpdate(DomainObject obj, PreparedStatement stmt) throws SQLException;

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
