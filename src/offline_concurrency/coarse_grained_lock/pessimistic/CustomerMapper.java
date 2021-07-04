package offline_concurrency.coarse_grained_lock.pessimistic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerMapper extends AbstractMapper {
  public Customer find(Long id) {
    Customer customer = (Customer) abstractFind(id);
    AddressMapper addressMapper = (AddressMapper) MapperRegistry.getMapper("AddressMapper");
    List<Address> addressList = addressMapper.findByCustomerId(customer.getId());
    for (Address address : addressList) {
      address.setCustomer(customer);
      customer.getAddressList().add(address);
    }
    return customer;
  }
  @Override
  protected String findStatement() {
    return "SELECT id, versionid, name FROM customer2 WHERE id=?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    return new Customer(id, rs.getString("name"));
  }

  public void update(Customer customer) {
    abstractUpdate(customer);
  }

  @Override
  protected String updateStatement() {
    return "UPDATE customer2 SET name=? WHERE id=?";
  }

  @Override
  protected void doUpdate(DomainObject object, PreparedStatement stmt) throws SQLException {
    Customer customer = (Customer) object;
    stmt.setString(1, customer.getName());
    stmt.setLong(2, customer.getId());
  }
}
