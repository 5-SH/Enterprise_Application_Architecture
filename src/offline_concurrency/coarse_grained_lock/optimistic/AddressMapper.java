package offline_concurrency.coarse_grained_lock.optimistic;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressMapper extends AbstractMapper {
  public AddressMapper() {
    super();
  }

  @Override
  protected String findStatement() {
    return null;
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

  public void deleteAddress(DomainObject object) {
    try {
      doDelete(object);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
