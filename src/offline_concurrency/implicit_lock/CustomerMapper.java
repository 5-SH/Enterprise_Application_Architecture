package offline_concurrency.implicit_lock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper extends AbstractMapper {
  public Customer find(Long id) {
    return (Customer) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT * FROM customer WHERE id=?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    return new Customer(id, rs.getString("name"));
  }

  public void update(DomainObject obj) {
    Customer customer = (Customer) obj;
    abstractUpdate(customer);
  }

  @Override
  protected String updateStatement() {
    return "UPDATE customer SET name=? WHERE id=?";
  }

  @Override
  protected void doUpdate(DomainObject object, PreparedStatement stmt) throws SQLException {
    Customer customer = (Customer) object;
    stmt.setString(1, customer.getName());
    stmt.setLong(2, customer.getId());
  }

  @Override
  public void insert(DomainObject obj) {

  }

  @Override
  public void delete(DomainObject obj) {

  }
}



