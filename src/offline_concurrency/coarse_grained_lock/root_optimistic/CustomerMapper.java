package offline_concurrency.coarse_grained_lock.root_optimistic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper extends Mapper {
  @Override
  protected String findStatement() {
    return "SELECT id, name FROM customer2 WHERE id = ?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    return null;
  }

  @Override
  protected String insertStatement() throws SQLException {
    return "INSERT INTO customer2 (id, name) VALUES (?, ?)";
  }

  @Override
  protected void doInsert(DomainObject obj, PreparedStatement stmt) throws SQLException {
    Customer customer = (Customer) obj;
    stmt.setString(2, customer.getName());
  }

  @Override
  protected String updateStatement() throws SQLException {
    return "UPDATE customer2 SET name = ? WHERE id = ?";
  }

  @Override
  protected void doUpdate(DomainObject obj, PreparedStatement stmt) throws SQLException {

  }

  @Override
  protected String deleteStatement() throws SQLException {
    return null;
  }
}
