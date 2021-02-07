package object_relation.lazy_load.ghost;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
  public interface DataSourceInterface {
    void load(DomainObject obj);
  }

  private static DataSourceInterface instance = null;

  public static void load(DomainObject obj) {
    if (instance == null) init();
    instance.load(obj);
  }

  public static void init() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      Connection db = DriverManager.getConnection(url, props);

      EmployeeMapper employeeMapper = new EmployeeMapper();
      employeeMapper.setConnection(db);
      DepartmentMapper departmentMapper = new DepartmentMapper();
      departmentMapper.setConnection(db);
      TimeRecordMapper timeRecordMapper = new TimeRecordMapper();
      timeRecordMapper.setConnection(db);

      MapperRegistry mapperRegistry = new MapperRegistry();
      mapperRegistry.setMapper("object_relation.lazy_load.ghost.Employee", employeeMapper);
      mapperRegistry.setMapper("object_relation.lazy_load.ghost.Department", departmentMapper);
      mapperRegistry.setMapper("object_relation.lazy_load.ghost.TimeRecord", timeRecordMapper);

      instance = mapperRegistry;
    } catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }
  }
}
