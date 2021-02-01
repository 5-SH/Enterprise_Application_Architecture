package object_relation.lazy_load.lazy_initialize;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductGateway {
  private Connection db;

  public ProductGateway() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      this.db = DriverManager.getConnection(url, props);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  public List findForSupplier(long id) throws SQLException {
    String sql = "SELECT p.id, p.name, p.type FROM supplier s, products p WHERE s.id = ? AND s.product_id = p.id";
    PreparedStatement stmt = db.prepareStatement(sql);
    stmt.setLong(1, id);
    ResultSet rs = stmt.executeQuery();

    List result = new ArrayList();
    while (rs.next()) {
      long productId = rs.getLong(1);
      String productName = rs.getString(2);
      String productType = rs.getString(3);

      result.add(new Product(productId, productName, productType));
    }

    return result;
  }
}
