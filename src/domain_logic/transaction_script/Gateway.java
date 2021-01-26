package domain_logic.transaction_script;

import java.sql.*;
import java.util.Properties;

public class Gateway {
  private Connection db;

  public Gateway() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      this.db = DriverManager.getConnection(url, props);
    } catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }
  }

  public ResultSet findRecognitionsFor(long contractID, MfDate asOf) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(findRecognitionsStatement);
    stmt.setLong(1, contractID);
    // TODO: MfDate 확인
    stmt.setDate(2, asOf.toSqlDate());
    ResultSet result = stmt.executeQuery();
    return result;
  }

  private static final String findRecognitionsStatement =
    "SELECT amount " +
      "From revenueRecognitions " +
      "WHERE contract = ? AND recognizedOn <= ?";
}
