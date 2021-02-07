package object_relation.lazy_load.ghost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class Mapper {
  private Map<Long, DomainObject> loadedMap = new HashMap<>();
  private Connection db;

  public void setConnection(Connection db) {
    this.db = db;
  }

  public DomainObject AbstractFind(Long key) {
    DomainObject result;
    result = (DomainObject) loadedMap.get(key);
    if (result == null) {
      result = createGhost(key);
      loadedMap.put(key, result);
    }
    return result;
  }

  public abstract DomainObject createGhost(Long key);


  public void load(DomainObject obj) {
    if (!obj.isGhost()) return;
    try {
      PreparedStatement stmt = db.prepareStatement(findStatement());
      stmt.setLong(1, obj.getKey());
      ResultSet rs = stmt.executeQuery();
      rs.next();
      loadLine(rs, obj);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected abstract String findStatement();

  public void loadLine(ResultSet rs, DomainObject obj) {
    if (obj.isGhost()) {
      obj.markLoading();
      doLoadLine(rs, obj);
      obj.markLoaded();
    }
  }

  protected abstract void doLoadLine(ResultSet rs, DomainObject obj);

  public Connection getDb() {
    return db;
  }
}
