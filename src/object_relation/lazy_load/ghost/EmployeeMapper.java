package object_relation.lazy_load.ghost;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper extends Mapper {
  public Employee find(Long key) {
    return (Employee) AbstractFind(key);
  }

  @Override
  public DomainObject createGhost(Long key) {
    return new Employee(key);
  }

  @Override
  protected String findStatement() {
    return "SELECT * FROM employee WHERE id = ?";
  }

  @Override
  protected void doLoadLine(ResultSet rs, DomainObject obj) {
    try {
      Employee employee = (Employee) obj;
      employee.setName(rs.getString(2));
//      DepartmentMapper departmentMapper = (DepartmentMapper) MapperRegistry.mapper();
//      employee.setDepartment(departmentMapper.find(rs.getLong(3)));
//      loadTimeRecords(employee);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void loadTimeRecords(Employee employee) {

  }
}
