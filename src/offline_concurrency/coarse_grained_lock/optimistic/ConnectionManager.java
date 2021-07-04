package offline_concurrency.coarse_grained_lock.optimistic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
  private static ConnectionManager soleInstance = new ConnectionManager();
  protected Connection conn;

  public ConnectionManager() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      this.conn = DriverManager.getConnection(url, props);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  private static ConnectionManager getInstance() { return soleInstance; }

  public static void initialize() { soleInstance = new ConnectionManager(); }

  public static Connection getConnection() { return getInstance().conn; }
}
