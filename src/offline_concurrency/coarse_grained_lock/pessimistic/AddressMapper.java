package offline_concurrency.coarse_grained_lock.pessimistic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressMapper extends AbstractMapper {
  public AddressMapper() {
    super();
  }

  public List<Address> findByCustomerId(Long id) {
    List<Address> addressList = new ArrayList<>();
    try {
      PreparedStatement stmt = conn.prepareStatement("SELECT id, line1, state, versionid FROM address WHERE customerid = ?");
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        addressList.add(new Address(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return addressList;
  }

  @Override
  protected String findStatement() {
    return "SELECT id, line1, state, customerid, versionid FROM address WHERE id = ?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    return null;
  }

  @Override
  protected void doUpdate(DomainObject object, PreparedStatement stmt) throws SQLException {

  }

  //  @Override
//  protected String insertStatement() {
//    return "INSERT INTO address (id, line1, city, state, customerid, versionid) VALUES (?, ?, ?, ?, ?, ?)";
//  }
//
//  @Override
//  protected void doInsert(DomainObject object) throws SQLException {
//    PreparedStatement stmt = conn.prepareStatement(insertStatement());
//    stmt.setLong(1, object.getId());
//    Address address = (Address) object;
//    stmt.setString(2, address.getLine1());
//    stmt.setString(3, address.getCity());
//    stmt.setString(4, address.getState());
//    stmt.setLong(5, address.getCustomer().getId());
//    stmt.setLong(6, address.getVersion().getId());
//    stmt.executeUpdate();
//  }

//  public void insertAddress(DomainObject object) {
//    try {
//      doInsert(object);
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//  }

  @Override
  protected String updateStatement() {
    return "UPDATE Address SET line1 = ?, city = ?, state = ? WHERE id = ? AND versionid = ?";
  }

//  @Override
//  protected void doUpdate(DomainObject object) throws SQLException {
//    PreparedStatement stmt = conn.prepareStatement(updateStatement());
//    Address address = (Address) object;
//    stmt.setString(1, address.getLine1());
//    stmt.setString(2, address.getCity());
//    stmt.setString(3, address.getState());
//    stmt.setLong(4, address.getId());
//    stmt.setLong(5, address.getVersion().getId());
//    stmt.executeUpdate();
//  }

//  public void updateAddress(DomainObject object) {
//    try {
//      doUpdate(object);
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//  }

//  @Override
//  protected String deleteStatement() {
//    return "DELETE FROM address WHERE id = ?";
//  }
//
//  @Override
//  protected void doDelete(DomainObject object) throws SQLException {
//    PreparedStatement stmt = conn.prepareStatement(deleteStatement());
//    stmt.setLong(1, object.getId());
//    stmt.executeUpdate();
//  }
//
//  public void deleteAddress(DomainObject object) {
//    try {
//      doDelete(object);
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//  }
}
