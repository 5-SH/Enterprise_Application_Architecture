package offline_concurrency.coarse_grained_lock.optimistic;

import java.sql.SQLException;

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

  @Override
  protected DomainObject doFind(Long id) throws SQLException {
    try {
      throw new Exception("NOT USED METHOD");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected String insertStatement() {
    try {
      throw new Exception("NOT USED METHOD");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void doInsert(DomainObject object) throws SQLException {
    try {
      throw new Exception("NOT USED METHOD");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected String updateStatement() {
    try {
      throw new Exception("NOT USED METHOD");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void doUpdate(DomainObject object) throws SQLException {
    try {
      throw new Exception("NOT USED METHOD");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected String deleteStatement() {
    try {
      throw new Exception("NOT USED METHOD");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void doDelete(DomainObject object) throws SQLException {
    try {
      throw new Exception("NOT USED METHOD");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
