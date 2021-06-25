package offline_concurrency.optimistic_offline_lock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper extends AbstractMapper {
  public CustomerMapper(String table, String[] columns) {
    super(table, columns);
  }

  @Override
  protected offline_concurrency.optimistic_offline_lock.DomainObject load(Long id, ResultSet rs) throws SQLException {
    String name = rs.getString(2);
    return new offline_concurrency.optimistic_offline_lock.Customer(id, name);
  }

  @Override
  protected void doInsert(offline_concurrency.optimistic_offline_lock.DomainObject object, PreparedStatement stmt) throws Exception {
    offline_concurrency.optimistic_offline_lock.Customer customer = (offline_concurrency.optimistic_offline_lock.Customer) object;
    stmt.setString(2, customer.getName());
  }

  @Override
  protected void doUpdate(DomainObject object, PreparedStatement stmt) throws Exception {
    offline_concurrency.optimistic_offline_lock.Customer customer = (Customer) object;
    stmt.setString(2, customer.getName());
  }
}
