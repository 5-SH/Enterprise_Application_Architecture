package offline_concurrency.coarse_grained_lock.optimistic;

import java.sql.*;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMapper {
  protected Map loadedMap = new HashMap();
  protected Connection conn;

  private static final String UPDATE_SQL = "UPDATE version SET VALUE =?, modifiedBy =?, modified = ? " + "WHERE id = ? and value = ?";
  private static final String DELETE_SQL = "DELETE FROM version WHERE id = ? and value =?";
  private static final String INSERT_SQL = "INSERT INTO version VALUES (?, ?, ?, ?)";
  private static final String LOAD_SQL = " SELECT id, value, modifiedBy, modified FROM version WHERE id = ?";

  public AbstractMapper() {
    this.conn = ConnectionManager.getConnection();
  }

  abstract protected String findStatement();

  protected Version loadVersion(Long id) {
    Version version = null;
    try {
      conn = ConnectionManager.getConnection();
      PreparedStatement stmt = conn.prepareStatement(LOAD_SQL);
      stmt.setLong(1, id.longValue());
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        long value = rs.getLong(2);
        String modifiedBy = rs.getString(3);
        Timestamp modified = rs.getTimestamp(4);
        version = new Version(id, value, modifiedBy, modified);
        AppSessionManager.getSession().putVersion(id, version);
      } else {
        throw new Exception("version " + id + " not found.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return version;
  }

  protected void insertVersion(Version version) {
    if (version.isNew()) {
      try {
        PreparedStatement stmt = conn.prepareStatement(INSERT_SQL);
        stmt.setLong(1, version.getId());
        stmt.setLong(2, version.getValue());
        stmt.setString(3, version.getModifiedBy());
        stmt.setTimestamp(4, version.getModified());
        stmt.executeUpdate();
        AppSessionManager.getSession().putVersion(version.getId(), version);
        version.setNew(false);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  protected void incrementVersion(Version version) {
    if (!version.isLocked()) {
      try {
        PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL);
        stmt.setLong(1, version.getValue() + 1);
        stmt.setString(2, version.getModifiedBy());
        stmt.setTimestamp(3, version.getModified());
        stmt.setLong(4, version.getId());
        stmt.setLong(5, version.getValue());
        int rowCount = stmt.executeUpdate();
        if (rowCount == 0) {
          throwConcurrencyException(version);
        }
        version.increaseValue();
        version.setLocked(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  protected void deleteVersion(Version version) {
    if (!version.isNew()) {
      try {
        PreparedStatement stmt = conn.prepareStatement(DELETE_SQL);
        stmt.setLong(1, version.getId());
        stmt.setLong(2, version.getValue());
        int rowCount = stmt.executeUpdate();
        if (rowCount == 0) {
          throwConcurrencyException(version);
        }
        AppSessionManager.getSession().deleteVersion(version.getId());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  abstract protected String insertStatement();
  abstract protected void doInsert(DomainObject object) throws SQLException;
  public void insert(DomainObject object) {
    insertVersion(object.getVersion());
    try {
      doInsert(object);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  abstract protected String updateStatement();
  abstract protected void doUpdate(DomainObject object) throws SQLException;
  public void update(DomainObject object) {
    incrementVersion(object.getVersion());
    try {
      doUpdate(object);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  abstract protected String deleteStatement();
  abstract protected void doDelete(DomainObject object) throws SQLException;
  public void delete(DomainObject object) {
    deleteVersion(object.getVersion());
    try {
      doDelete(object);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void throwConcurrencyException(Version version){
    Version currentVersion = Version.find(version.getId());
    try {
      throw new Exception("version modified by " + currentVersion.getModifiedBy() + " at " +
        DateFormat.getDateTimeInstance().format(currentVersion.getModified()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
