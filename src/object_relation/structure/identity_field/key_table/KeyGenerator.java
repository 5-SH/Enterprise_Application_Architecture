package object_relation.structure.identity_field.key_table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KeyGenerator {
  private Connection conn;
  private String keyName;
  private long nextID;
  private long maxID;
  private int incrementBy;

  public KeyGenerator(Connection conn, String keyName, int incrementBy) {
    this.conn = conn;
    this.keyName = keyName;
    this.incrementBy = incrementBy;
    nextID = maxID = 0;
    try {
      conn.setAutoCommit(false);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public synchronized Long nextKey() {
    if (nextID == maxID) {
      reserveIDs();
    }
    return new Long(nextID++);
  }

  private void reserveIDs() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    long newNextID = 0;
    try {
      stmt = conn.prepareStatement("SELECT nextID FROM `keys` WHERE name = ? FOR UPDATE");
      stmt.setString(1, keyName);
      rs = stmt.executeQuery();
      rs.next();
      newNextID = rs.getLong(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    long newMaxID = newNextID + incrementBy;
    stmt = null;
    try {
      stmt = conn.prepareStatement("UPDATE `keys` SET nextID = ? WHERE name = ?");
      stmt.setLong(1, newMaxID);
      stmt.setString(2, keyName);
      stmt.executeUpdate();
      conn.commit();
      nextID = newNextID;
      maxID = newMaxID;
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
