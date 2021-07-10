package offline_concurrency.coarse_grained_lock.optimistic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressMapper extends AbstractMapper {
  public AddressMapper() {
    super();
  }

  @Override
  protected String findStatement() {
    return "SELECT id, line1, city, state, cutomerid, versionId FROM address WHERE id = ?";
  }

  @Override
  protected DomainObject doFind(Long id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(findStatement());
    stmt.setLong(1, id);
    ResultSet rs = stmt.executeQuery();

    Long addressId = rs.getLong(1);
    String line1 = rs.getString(2);
    String city = rs.getString(3);
    String state = rs.getString(4);
    Long customerId = rs.getLong(5);
    Long versionId = rs.getLong(6);

    return new Address(addressId, Version.find(versionId), line1, city, state);
  }

  @Override
  protected String insertStatement() {
    return "INSERT INTO address (id, line1, city, state, customerid, versionid) VALUES (?, ?, ?, ?, ?, ?)";
  }

  @Override
  protected void doInsert(DomainObject object) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(insertStatement());
    stmt.setLong(1, object.getId());
    Address address = (Address) object;
    stmt.setString(2, address.getLine1());
    stmt.setString(3, address.getCity());
    stmt.setString(4, address.getState());
    stmt.setLong(5, address.getCustomer().getId());
    stmt.setLong(6, address.getVersion().getId());
    stmt.executeUpdate();
  }

  public void insertAddress(DomainObject object) {
    try {
      doInsert(object);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected String updateStatement() {
    return "UPDATE Address SET line1 = ?, city = ?, state = ? WHERE id = ? AND versionid = ?";
  }

  @Override
  protected void doUpdate(DomainObject object) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(updateStatement());
    Address address = (Address) object;
    stmt.setString(1, address.getLine1());
    stmt.setString(2, address.getCity());
    stmt.setString(3, address.getState());
    stmt.setLong(4, address.getId());
    stmt.setLong(5, address.getVersion().getId());
    stmt.executeUpdate();
  }

  public void updateAddress(DomainObject object) {
    try {
      doUpdate(object);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected String deleteStatement() {
    return "DELETE FROM address WHERE id = ?";
  }

  @Override
  protected void doDelete(DomainObject object) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(deleteStatement());
    stmt.setLong(1, object.getId());
    stmt.executeUpdate();
  }

  public List findAddressByCustomerId(Long cutomerId, Customer customer) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement("SELECT id, line1, city, state, versionId FROM address WHERE customerid = ?");
    stmt.setLong(1, cutomerId);
    ResultSet rs = stmt.executeQuery();

    List<Address> addressList = new ArrayList<Address>();
    while(rs.next()) {
      Long addressId = rs.getLong(1);
      String line1 = rs.getString(2);
      String city = rs.getString(3);
      String state = rs.getString(4);
      Long versionId = rs.getLong(5);

      Address addr = new Address(addressId, Version.find(versionId), line1, city, state);
      addr.setCustomer(customer);
      addressList.add(addr);
    }

    return addressList;
  }
}
