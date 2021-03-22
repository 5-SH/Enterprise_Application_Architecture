package object_relation.structure.association_table_mapping;

public class MapperRegistry {
  private static MapperRegistry instance = new MapperRegistry();
  protected EmployeeMapper employeeMapper = new EmployeeMapper();
  protected SkillMapper skillMapper = new SkillMapper();
  protected JoinEmployeeMapper joinEmployeeMapper = new JoinEmployeeMapper();

  private static MapperRegistry getInstance() { return instance; }
  public static EmployeeMapper employee() { return getInstance().employeeMapper; }
  public static SkillMapper skill() { return getInstance().skillMapper; }
  public static JoinEmployeeMapper joinEmployee() { return getInstance().joinEmployeeMapper; }
}
