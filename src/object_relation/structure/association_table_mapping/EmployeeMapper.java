package object_relation.structure.association_table_mapping;

import object_relation.structure.foreign_key_mapping.single_valued_reference.Artist;

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

  @Override
  protected void save(DomainObject arg) {
    PreparedStatement stmt = null;
    try {
      Employee employee = (Employee) arg;
      for (Skill s : employee.getSkills()) {
        stmt = DB.prepareStatement("UPDATE employeeSkill SET employeeID = ? WHERE skillID = ?");
        stmt.setLong(2, s.getId());
        stmt.setLong(1, employee.getId());
        stmt.execute();
      }
      saveSkills(employee);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void saveSkills(Employee emp) {
    deleteSkills(emp);
    List<Skill> skills = emp.getSkills();
    for (Skill s : skills) {
      MapperRegistry.skill().insert(emp.getId(), s.getId());
    }
  }

  private void deleteSkills(Employee emp) {
    PreparedStatement stmt = null;
    try {
      stmt = DB.prepareStatement("DELETE FROM employeeSkill WHERE employeeID = ?");
      stmt.setLong(1, emp.getId().longValue());
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
