package object_relation.behavior.lazy_load.lazy_initialize;

import java.sql.SQLException;
import java.util.List;

public class Tester {
  public static void main(String[] args) throws SQLException {
    Supplier supplier = new Supplier(1, "ms");
    List<Product> products = supplier.getProducts();
    for (Product p : products) {
      System.out.println(p.toString());
    }
  }
}
