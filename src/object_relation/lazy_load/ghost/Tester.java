package object_relation.lazy_load.ghost;

public class Tester {
  public static void main(String[] args) {
    DataSource.init();

    // get employee
    EmployeeMapper employeeMapper = (EmployeeMapper) MapperRegistry.mapper("object_relation.lazy_load.ghost.Employee");
    Employee employee1 = employeeMapper.find(new Long(1));
    System.out.println(employee1.getName());
  }
}
