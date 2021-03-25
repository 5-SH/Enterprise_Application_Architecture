package object_relation.structure.association_table_mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// 여러 직원을 쿼리 하나로 처리
public class JoinEmployeeMapper extends AbstractMapper {
  public List findAll() {
    return findAll(findAllStatement());
  }

  private String findAllStatement() {
    return "SELECT " +  "employee.ID, employee.name, " +
      "es.skillID, es.employeeID, skill.ID skillID, " +
      "skill.name skillName " +
      "FROM employee, skill, employeeSkill es " +
      "WHERE employee.ID = es.employeeID AND skill.ID = es.skillID " +
      "ORDER BY employee.name";
  }

  protected List findAll(String sql) {
    AssociationTableLoader loader = new AssociationTableLoader((JoinEmployeeMapper) this, new SkillAdder());
    return loader.run(findAllStatement());
  }

  private static class SkillAdder implements AssociationTableLoader.Adder {
    @Override
    public void add(DomainObject host, ResultSet rs) throws SQLException {
      Employee emp = (Employee) host;
      Long skillId = new Long(rs.getLong("SkillID"));
      emp.addSkill((Skill) MapperRegistry.skill().loadRow(skillId, rs));
    }
  }

  public Employee find(long id) {
    return (Employee) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT " + "employee.ID, employee.name, " +
      "es.skillID, es.employeeID, skill.ID skillID, " +
      "skill.name skillName " +
      "FROM employee, skill, employeeSkill es " +
      "WHERE employee.ID = es.employeeID AND skill.ID = es.skillID AND employee.ID = ?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    Employee result = (Employee) loadRow(id, rs);
    while (rs.next()) {
      loadSkillData(result, rs);
    }
    return result;
  }

  protected DomainObject loadRow(long id, ResultSet rs) throws SQLException {
    Employee result = new Employee(id);
    result.setName(rs.getString("name"));
    return result;
  }

  private boolean rowIsForSameEmployee(Long id, ResultSet rs) throws SQLException {
    return id.equals(new Long(rs.getLong(1)));
  }

  private void loadSkillData(Employee person, ResultSet rs) throws SQLException {
    Long skillID = new Long(rs.getLong("skillID"));
    person.addSkill ((Skill) MapperRegistry.skill().loadRow(skillID, rs));
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
