package offline_concurrency.coarse_grained_lock.pessimistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMapper {
  protected Map loadedMap = new HashMap();
  protected Connection conn;

  public AbstractMapper() {
    this.conn = ConnectionManager.getConnection();
  }

  abstract protected String findStatement();

  protected DomainObject abstractFind(Long id) {
    DomainObject result = (DomainObject) loadedMap.get(id);
    if (result != null) return result;
    try {
      PreparedStatement stmt = conn.prepareStatement(findStatement());
      stmt.setLong(1, id.longValue());
      ResultSet rs = stmt.executeQuery();
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
    loadedMap.put(id, result);
    return result;
  }

  protected abstract DomainObject doLoad(Long id, ResultSet rs) throws SQLException;

  abstract protected String updateStatement();

  protected void abstractUpdate(DomainObject object) {
    try {
      PreparedStatement stmt = conn.prepareStatement(updateStatement());
      doUpdate(object, stmt);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected abstract void doUpdate(DomainObject object, PreparedStatement stmt) throws SQLException;
}
