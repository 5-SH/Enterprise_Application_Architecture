package offline_concurrency.coarse_grained_lock.pessimistic;

import basic.plugin.IdGenerator;

import java.sql.*;
import java.text.DateFormat;

public class ExclusiveReadLockManagerDBImpl implements ExclusiveReadLockManager {
  public static ExclusiveReadLockManager INSTANCE = new ExclusiveReadLockManagerDBImpl();

  private static final String UPDATE_SQL = "UPDATE `version2` SET value = ? WHERE id = ? AND value = ? AND ownerid = ? AND targetid = ?";
  private static final String INSERT_SQL = "INSERT INTO `version2` VALUES (?, ?, ?, ?, ?, ?)";
  private static final String CHECK_SQL = "SELECT id, value FROM `version2` WHERE targetid = ?";
  private static final String DELETE_SQL = "DELETE FROM version2 WHERE id = ? AND value = ? AND ownerid = ?";
  private static final String DELETE_ALL_SQL = "DELETE FROM `version2` WHERE ownerid = ?";

  @Override
  public Version acquireLock(DomainObject object, String owner) throws Exception {
    Version version = null;
    if (!hasLock(object, owner)) {
      try {
        version = new Version(IdGenerator.INSTANCE.nextId(), 0, "admin", new Timestamp(System.currentTimeMillis()));

        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(INSERT_SQL);
        stmt.setLong(1, version.getId());
        stmt.setLong(2, version.getValue());
        stmt.setString(3, version.getModifiedBy());
        stmt.setTimestamp(4, version.getModified());
        stmt.setString(5, owner);
        stmt.setLong(6, object.getId());
        stmt.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return version;
  }

  @Override
  public void releaseLock(DomainObject object, String owner) throws Exception {
    try {
      Connection conn = ConnectionManager.getConnection();
      PreparedStatement stmt = conn.prepareStatement(DELETE_SQL);

      stmt.setLong(1, object.getVersion().getId());
      stmt.setLong(2, object.getVersion().getValue());
      stmt.setString(3, owner);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void releaseAllLocks(String owner) {
    try {
      Connection conn = ConnectionManager.getConnection();
      PreparedStatement stmt = conn.prepareStatement(DELETE_ALL_SQL);
      stmt.setString(1, owner);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void increaseLock(DomainObject object, String owner) {
    Version version = object.getVersion();
    if (!version.isLocked()) {
      try {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL);

        stmt.setLong(1, version.getValue() + 1);
        stmt.setLong(2, version.getId());
        stmt.setLong(3, version.getValue());
        stmt.setString(4, owner);
        stmt.setLong(5, object.getId());

        int rowCount = stmt.executeUpdate();
        if (rowCount == 0) {
          throwConcurrencyException(version);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private boolean hasLock(DomainObject object, String owner) {
    boolean hasLock = false;
    try {
      Connection conn = ConnectionManager.getConnection();
      PreparedStatement stmt = conn.prepareStatement(CHECK_SQL);
      stmt.setLong(1, object.getId());
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        hasLock = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return hasLock;
  }

  private void throwConcurrencyException(Version version){
    try {
      throw new Exception("version modified by " + version.getModifiedBy() + " at " +
        DateFormat.getDateTimeInstance().format(version.getModified()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
