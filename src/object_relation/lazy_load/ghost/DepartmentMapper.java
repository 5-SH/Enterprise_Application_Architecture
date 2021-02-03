package object_relation.lazy_load.ghost;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper extends Mapper {
  public Department find(Long key) {
    return (Department) AbstractFind(key);
  }

  @Override
  public DomainObject createGhost(Long key) {
    return new Department(key);
  }

  @Override
  protected String findStatement() {
    return null;
  }

  @Override
  protected void doLoadLine(ResultSet rs, DomainObject obj) {

  }
}
