package object_relation.structure.association_table_mapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MapperRegistry {
  private static MapperRegistry instance = new MapperRegistry();
  protected EmployeeMapper employeeMapper = new EmployeeMapper();
  protected SkillMapper skillMapper = new SkillMapper();
  protected JoinEmployeeMapper joinEmployeeMapper = new JoinEmployeeMapper();
  protected Connection DB = null;

  private static MapperRegistry getInstance() { return instance; }
  public static EmployeeMapper employee() { return getInstance().employeeMapper; }
  public static SkillMapper skill() { return getInstance().skillMapper; }
  public static JoinEmployeeMapper joinEmployee() { return getInstance().joinEmployeeMapper; }
  public static Connection DB() {
    if (getInstance().DB != null) {
      return getInstance().DB;
    }

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      getInstance().DB = DriverManager.getConnection(url, props);
    } catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }

    return getInstance().DB;
  }
}
