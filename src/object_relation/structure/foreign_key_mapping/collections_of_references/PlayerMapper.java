package object_relation.structure.foreign_key_mapping.collections_of_references;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerMapper extends AbstractMapper {
  public Player find(long id) {
    return (Player) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT id, name, teamID FROM player WHERE id = ?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    String name = rs.getString(2);
    Long teamID = new Long(rs.getLong(3));
    Player player = new Player(id, name, teamID);
    return player;
  }

  public List findForTeam(Long key) throws SQLException {
    List<Player> result = new ArrayList<>();
    PreparedStatement stmt = DB.prepareStatement("SELECT id, name, teamID FROM player WHERE teamID = ?");
    stmt.setLong(1, key);
    ResultSet rs = stmt.executeQuery();
    while(rs.next()) {
      Long id = new Long(rs.getLong(1));
      Player player;
      if (loadedMap.containsKey(id)) {
        player = find(id);
      } else {
        player = loadPlayer(id, rs);
      }
      result.add(player);
    }
    return result;
  }

  private Player loadPlayer(long id, ResultSet rs) throws SQLException {
    String name = rs.getString(2);
    Long teamID = new Long(rs.getLong(3));
    Player player = new Player(id, name, teamID);
    doRegister(id, player);
    return player;
  }

  @Override
  public void update(DomainObject arg) {
    PreparedStatement stmt = null;
    try {
      stmt = DB.prepareStatement("UPDATE player SET name = ?, teamID = ? WHERE id = ?");
      stmt.setLong(3, arg.getId().longValue());
      Player player = (Player) arg;
      stmt.setString(1, player.getName());
      stmt.setLong(2, player.getTeamID());
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void update(DomainObject arg, Long teamID) {
    PreparedStatement stmt = null;
    try {
      stmt = DB.prepareStatement("UPDATE player SET name = ?, teamID = ? WHERE id = ?");
      stmt.setLong(3, arg.getId().longValue());
      Player player = (Player) arg;
      stmt.setString(1, player.getName());
      stmt.setLong(2, teamID);
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void linkTeam(Player player, Long id) {
      update(player, id);
  }
}
