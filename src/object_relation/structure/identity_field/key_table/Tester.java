package object_relation.structure.identity_field.key_table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Tester {
  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      Connection db = DriverManager.getConnection(url, props);

      KeyGenerator keyGenerator = new KeyGenerator(db, "orders", 1);
      System.out.println(keyGenerator.nextKey());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
