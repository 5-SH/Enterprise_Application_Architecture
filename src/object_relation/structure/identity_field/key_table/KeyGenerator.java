package object_relation.structure.identity_field.key_table;

import java.sql.Connection;
import java.sql.SQLException;

public class KeyGenerator {
  private Connection conn;
  private String keyName;
  private long nextId;
  private long maxId;
  private int incrementBy;

  public KeyGenerator(Connection conn, String keyName, int incrementBy) {
    this.conn = conn;
    this.keyName = keyName;
    this.incrementBy = incrementBy;
    nextId = maxId = 0;
    try {
      conn.setAutoCommit(false);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
