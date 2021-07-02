package offline_concurrency.coarse_grained_lock;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerMapper extends AbstractMapper {
  public CustomerMapper() {
    super();
  }

  @Override
  protected String findStatement() {
    return null;
  }

  @Override
  protected String insertStatement() {
    return "INSERT INTO customer2 (ID, name, versionid) VALUES(?, ?, ? )";
  }

  @Override
  protected void doInsert(DomainObject object) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(insertStatement());
    stmt.setLong(1, object.getId());
    Customer customer = (Customer) object;
    stmt.setString(2, customer.getName());
    stmt.setLong(3, customer.getVersion().getId());
    stmt.executeUpdate();

    insertAddress(customer);
  }

  private void insertAddress(Customer customer) throws SQLException {
    List<Address> addressList = customer.getAddressList();
    for (Address address : addressList) {
      AddressMapper addressMapper = (AddressMapper) MapperRegistry.getMapper("AddressMapper");
      addressMapper.insertAddress(address);
    }
  }
}
