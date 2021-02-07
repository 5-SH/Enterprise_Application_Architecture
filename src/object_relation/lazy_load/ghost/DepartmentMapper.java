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
    return "SELECT * FROM department WHERE id = ?";
  }

  @Override
  protected void doLoadLine(ResultSet rs, DomainObject obj) {
    try {
      Department department = (Department) obj;
      department.setName(rs.getString(2));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
