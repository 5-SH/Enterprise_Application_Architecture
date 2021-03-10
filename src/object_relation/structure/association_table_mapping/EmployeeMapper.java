package object_relation.structure.association_table_mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper extends AbstractMapper {
  public Employee find(long id) {
    return (Employee) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT id, name FROM employee WHERE id = ?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    String name = rs.getString(2);
    Employee emp = new Employee(id, name);
    loadSkills(emp);
    return emp;
  }

  private void loadSkills(Employee emp) throws SQLException {
    ResultSet rs = skillLinkRows(emp.getId());
    List<Skill> skills = new ArrayList<>();
    while (rs.next()) {
      long skillID = rs.getLong(2);
      Skill skill = MapperRegistry.skill().find(skillID);
      skills.add(skill);
    }
    emp.setSkills(skills);
  }

  private ResultSet skillLinkRows(long id) throws SQLException{
    List<Skill> result = new ArrayList<>();
    PreparedStatement stmt = DB.prepareStatement("SELECT employeeID, skillID FROM employeeSkill WHERE employeeID = ?");
    stmt.setLong(1, id);
    ResultSet rs = stmt.executeQuery();
    return rs;
  }
}
