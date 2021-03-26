package object_relation.structure.embeded_value;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product extends DomainObject {
  private String name;
  private String type;

  public Product(Long id, String name, String type) {
    super(id);
    this.name = name;
    this.type = type;
  }

  private final static String findStatementString = "SELECT id, name, type FROM products WHERE id = ?";

  public static Product find(Long id) {
    Product result = Registry.getProduct(id);
    if (result != null) return result;
    try {
      PreparedStatement stmt = Registry.DB().prepareStatement(findStatementString);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = load(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  public static Product find(long id) { return find(new Long(id)); }

  public static Product load(ResultSet rs) throws SQLException {
    Long id = new Long(rs.getLong(1));
    Product result = Registry.getProduct(id);
    if (result != null) return result;

    String name = rs.getString(2);
    String type = rs.getString(3);
    result = new Product(id, name, type);
    Registry.addProduct(result);

    return result;
  }

  @Override
  public String toString() {
    return "Product{" +
      "name='" + name + '\'' +
      ", type='" + type + '\'' +
      '}';
  }
}
