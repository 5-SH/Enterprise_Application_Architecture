package object_relation.structure.identity_field.compound_key;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper extends AbstractMapper {
  public Order find(Key key) {
    return (Order) abstractFind(key);
  }

  public Order find(Long id) {
    return find(new Key(id));
  }

  @Override
  protected String findStatementString() {
    return "SELECT id, customer from orders WHERE id = ?";
  }

  @Override
  protected DomainObjectWithKey doLoad(Key key, ResultSet rs) throws SQLException {
    String customer = rs.getString("customer");
    Order result = new Order(key, customer);
    MapperRegistry.lineItem().loadAllLineItemsFor(result);
    loadedMap.put(key, result);
    return result;
  }
}
