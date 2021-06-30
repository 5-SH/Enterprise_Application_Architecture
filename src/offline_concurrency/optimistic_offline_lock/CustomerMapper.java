package offline_concurrency.optimistic_offline_lock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper extends AbstractMapper {
  public CustomerMapper(String table, String[] columns) {
    super(table, columns);
  }

  @Override
  protected DomainObject load(Long id, ResultSet rs) throws SQLException {
    String name = rs.getString(2);
    return new Customer(id, name);
  }

  @Override
  protected void doInsert(DomainObject object, PreparedStatement stmt) throws Exception {
    Customer customer = (Customer) object;
    stmt.setString(2, customer.getName());
  }

  @Override
  protected void doUpdate(DomainObject object, PreparedStatement stmt) throws Exception {
    Customer customer = (Customer) object;
    stmt.setString(2, customer.getName());
  }
}
