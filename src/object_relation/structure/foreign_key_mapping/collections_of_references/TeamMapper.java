package object_relation.structure.foreign_key_mapping.collections_of_references;

import java.sql.PreparedStatement;
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

  @Override
  public void update(DomainObject arg) {
    PreparedStatement stmt = null;
    try {
      stmt = DB.prepareStatement("UPDATE team SET name = ? WHERE id = ?");
      stmt.setLong(2, arg.getId().longValue());
      Team team = (Team) arg;
      stmt.setString(1, team.getName());
      stmt.execute();
      savePlayers(team);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void savePlayers(Team team) {
    for (Object o : team.getPlayers()) {
      Player p = (Player) o;
      MapperRegistry.player().linkTeam(p, team.getId());
    }
  }
}
