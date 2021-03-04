package object_relation.structure.identity_field.compound_key;

import java.sql.PreparedStatement;
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

  @Override
  protected String insertStatementString() {
    return "INSERT INTO orders VALUES(?,?)";
  }

  @Override
  protected void insertData(DomainObjectWithKey abstractSubject, PreparedStatement stmt) throws SQLException {
    try {
      Order subject = (Order) abstractSubject;
      stmt.setString(2, subject.getCustomer());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Key findNextDatabaseKeyObject() throws SQLException {
    String findNextKeyStatementString = "SELECT MAX(ID) FROM orders";
    PreparedStatement stmt = DB.prepareStatement(findNextKeyStatementString);
    ResultSet rs = stmt.executeQuery();
    rs.next();
    return new Key(new Long(rs.getLong(1) + 1));
  }

  @Override
  protected String updateStatementString() {
    return "UPDATE orders SET customer = ? WHERE id = ?";
  }

  @Override
  protected void loadUpdateStatement(DomainObjectWithKey subject, PreparedStatement stmt) throws SQLException {
    Order order = (Order) subject;
    stmt.setString(1, order.getCustomer());
    stmt.setLong(2, order.getKey().longValue());
  }
}
