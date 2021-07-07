package offline_concurrency.coarse_grained_lock.root_optimistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class Mapper {
  private Map<Long, DomainObject> loadedMap = new HashMap<>();
  private Connection db;

  public Mapper() {
    this.db = ConnectionManager.getConnection();
  }

  public void setConnection(Connection db) {
    this.db = db;
  }

  public DomainObject AbstractFind(Long id) {
    DomainObject result = (DomainObject) loadedMap.get(id);
    if (result != null) return result;
    try {
      PreparedStatement findStatement = db.prepareStatement(findStatement());
      findStatement.setLong(1, id);
      ResultSet rs = findStatement.executeQuery();
      rs.next();
      result = load(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  protected abstract String findStatement();

  public DomainObject load(ResultSet rs) throws SQLException{
    Long id = new Long(rs.getLong(1));
    if (loadedMap.containsKey(id)) return loadedMap.get(id);
    DomainObject result = doLoad(id, rs);
    loadedMap.put(id, result);

    return result;
  }

  abstract protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException;

  public void insert(DomainObject obj) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(insertStatement());
    stmt.setLong(1, obj.getId());
    doInsert(obj, stmt);
    stmt.executeUpdate();
    loadedMap.put(obj.getId(), obj);
  };

  abstract protected String insertStatement() throws SQLException;
  abstract protected void doInsert(DomainObject obj, PreparedStatement stmt) throws SQLException;

  public void update(DomainObject obj) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(updateStatement());
    doUpdate(obj, stmt);
    stmt.executeUpdate();
    if (!loadedMap.containsKey(obj.getId())) loadedMap.put(obj.getId(), obj);
  };

  abstract protected String updateStatement() throws SQLException;
  abstract protected void doUpdate(DomainObject obj, PreparedStatement stmt) throws SQLException;

  public void delete(DomainObject obj) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(deleteStatement());
    stmt.setLong(1, obj.getId());
    stmt.executeUpdate();
    if (loadedMap.containsKey(obj.getId())) loadedMap.remove(obj.getId());
  };

  abstract protected String deleteStatement() throws SQLException;
}
