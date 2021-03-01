package object_relation.behavior.lazy_load.ghost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

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
      DepartmentMapper departmentMapper = (DepartmentMapper) MapperRegistry.mapper("object_relation.behavior.lazy_load.ghost.Department");
      employee.setDepartment(departmentMapper.find(rs.getLong(3)));
      loadTimeRecords(employee);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void loadTimeRecords(Employee employee) {
    ListLoader loader = new ListLoader();
    loader.setSql(TimeRecordMapper.FIND_FOR_EMPLOYEE_SQL);
    loader.setSqlParams(Arrays.asList(employee.getKey()));
    loader.setMapper(MapperRegistry.mapper("object_relation.behavior.lazy_load.ghost.TimeRecord"));

    DomainList ghost = new DomainList(new Long(-1));
    ghost.setLoader(loader);
    employee.setTimeRecords(ghost);
  }
}
