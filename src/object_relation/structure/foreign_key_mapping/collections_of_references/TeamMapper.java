package object_relation.structure.foreign_key_mapping.collections_of_references;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeamMapper extends AbstractMapper {
  public Team find(long id) {
    return (Team) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT id, name FROM Team WHERE id = ?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    String name = rs.getString(2);
    List players = MapperRegistry.player().findForTeam(id);
    Team team = new Team(id, name, players);
    return team;
  }
}
