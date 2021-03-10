package object_relation.structure.association_table_mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillMapper extends AbstractMapper {
  public Skill find(long id) {
    return (Skill) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT id, name FROM skill WHERE id = ?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    String name = rs.getString(2);
    Skill result = new Skill(id, name);
    return result;
  }
}
