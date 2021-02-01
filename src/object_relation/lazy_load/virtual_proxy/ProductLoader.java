package object_relation.lazy_load.virtual_proxy;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class ProductLoader implements VirtualListLoader {
  private Long id;
  private ProductMapper productMapper;
  private Connection db;

  public ProductLoader(Long id) {
    this.id = id;
    this.productMapper = new ProductMapper();

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

  public List load() {
    ResultSet rs = null;
    try {
      String sql = "SELECT p.id, p.name, p.type FROM supplier s, products p WHERE s.id = ? AND s.product_id = p.id";
      PreparedStatement stmt = db.prepareStatement(sql);
      stmt.setLong(1, id);
      rs = stmt.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return productMapper.findForSupplier(id, rs);
  }
}
