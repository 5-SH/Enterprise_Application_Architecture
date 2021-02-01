package object_relation.lazy_load.value_holder;

import object_relation.lazy_load.lazy_initialize.Product;
import object_relation.lazy_load.value_holder.SupplierMapper;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class Tester {
  public static void main(String[] args) {
    // gateway
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      Connection db = DriverManager.getConnection(url, props);

      String sql = "SELECT DISTINCT(id), name FROM supplier WHERE id = ?";
      PreparedStatement stmt = db.prepareStatement(sql);
      stmt.setLong(1, 1);
      ResultSet rs = stmt.executeQuery();
      rs.next();

      SupplierMapper supplierMapper = new SupplierMapper();
      SupplierVH supplier = (SupplierVH) supplierMapper.doLoad(new Long(1), rs);
      List<Product> products = supplier.getProducts();

      for (Product p : products) {
        System.out.println(p.toString());
      }
    } catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }
  }
}
