package object_relation.structure.embeded_value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Registry {
  private static Registry soleInstance = new Registry();
  protected Map loadedProduct;
  protected Connection db;

  public Registry() {
    loadedProduct = new HashMap();
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      db = DriverManager.getConnection(url, props);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static Registry getInstance() { return soleInstance; }

  public static Connection DB() { return getInstance().db; }

  public static void initialize() { soleInstance = new Registry(); }

  public static void addProduct(Product product) {
    if (!getInstance().loadedProduct.containsKey(product.getId()))
      getInstance().loadedProduct.put(product.getId(), product);
  }

  public static Product getProduct(Long id) {
    return (Product) getInstance().loadedProduct.get(id);
  }
}
