package offline_concurrency.pessimistic_offline_lock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExclusiveReadLockManagerDBImpl implements ExclusiveReadLockManager {
  public static ExclusiveReadLockManager INSTANCE = new ExclusiveReadLockManagerDBImpl();

  private static final String INSERT_SQL = "INSERT INTO `lock` values(?, ?);";
  private static final String DELETE_SINGLE_SQL = "DELETE FROM `lock` WHERE lockableid = ? and ownerid = ?";
  private static final String DELETE_ALL_SQL = "DELETE FROM `lock` WHERE ownerid = ?";
  private static final String CHECK_SQL = "SELECT lockableid FROM `lock` WHERE lockableid = ? and ownerid = ?";

  @Override
  public void acquireLock(Long lockable, String owner) throws Exception {
    if (!hasLock(lockable, owner)) {
      try {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(INSERT_SQL);
        stmt.setLong(1, lockable);
        stmt.setString(2, owner);
        stmt.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void releaseLock(Long lockable, String owner) throws Exception {
    try {
      Connection conn = ConnectionManager.getConnection();
      PreparedStatement stmt = conn.prepareStatement(DELETE_SINGLE_SQL);
      stmt.setLong(1, lockable);
      stmt.setString(2, owner);
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

  private boolean hasLock(Long lockable, String owner) {
    boolean hasLock = false;
    try {
      Connection conn = ConnectionManager.getConnection();
      PreparedStatement stmt = conn.prepareStatement(CHECK_SQL);
      stmt.setLong(1, lockable);
      stmt.setString(2, owner);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        hasLock = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return hasLock;
  }
}
