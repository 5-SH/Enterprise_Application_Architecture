package offline_concurrency.coarse_grained_lock;

public class BaseMapper extends AbstractMapper {
  @Override
  protected String findStatement() {
    try {
      throw new Exception("NOT USED METHOD");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
