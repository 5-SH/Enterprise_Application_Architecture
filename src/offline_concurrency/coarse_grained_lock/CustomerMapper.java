package offline_concurrency.coarse_grained_lock;

public class CustomerMapper extends AbstractMapper {
  @Override
  protected String findStatement() {
    return null;
  }

  @Override
  protected String insertStatement() {
    return "INSERT INTO customer2 VALUES(?, ?, ?, ?, ?)";
  }
}
