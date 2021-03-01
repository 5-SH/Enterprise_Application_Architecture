package object_relation.behavior.lazy_load.virtual_proxy;

import object_relation.behavior.lazy_load.lazy_initialize.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
  public List findForSupplier(long id, ResultSet rs) {
    List result = new ArrayList();
    try {
      while (rs.next()) {
        long productId = rs.getLong(1);
        String productName = rs.getString(2);
        String productType = rs.getString(3);

        result.add(new Product(productId, productName, productType));

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
