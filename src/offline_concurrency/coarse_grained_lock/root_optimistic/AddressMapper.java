package offline_concurrency.coarse_grained_lock.root_optimistic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper extends Mapper {
  @Override
  protected String findStatement() {
    return "SELECT id, line1, city, state, customerid FROM address WHERE id = ?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    return null;
  }

  @Override
  protected String insertStatement() throws SQLException {
    return "INSERT INTO address (id, line1, city, state, customerid) VALUES (?, ?, ?, ?, ?)";
  }

  @Override
  protected void doInsert(DomainObject obj, PreparedStatement stmt) throws SQLException {

  }

  @Override
  protected String updateStatement() throws SQLException {
    return "UPDATE address SET line1 = ?, city = ?, state = ? WHERE id = ?";
  }

  @Override
  protected void doUpdate(DomainObject obj, PreparedStatement stmt) throws SQLException {

  }

  @Override
  protected String deleteStatement() throws SQLException {
    return null;
  }
}
