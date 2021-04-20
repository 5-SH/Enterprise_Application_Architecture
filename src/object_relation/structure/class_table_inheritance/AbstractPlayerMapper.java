package object_relation.structure.class_table_inheritance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractPlayerMapper extends Mapper {
  public static final String TABLENAME = "sports_player";
  abstract protected DomainObject createDomainObject();

  protected DomainObject abstractFind(long id, String tablename) throws SQLException {
    DomainObject result = (DomainObject) loadedMap.get(id);
    if (result != null) return result;

    ResultSet rs = findRow(id, tablename);
    if (rs == null) return null;

    result = createDomainObject();
    result.setId(rs.getLong("id"));
    load(result);
    return result;
  }

  protected void load(DomainObject obj) throws SQLException {
    ResultSet rs = findRow(obj.getId(), TABLENAME);
    Player player = (Player) obj;
    player.setName(rs.getString("name"));
    player.setType(rs.getString("type"));
  }

  @Override
  protected void update(DomainObject obj) {
    try {
      PreparedStatement stmt = DB.prepareStatement("UPDATE sports_player SET name = ?, type =? WHERE id = ?");
      Player player = (Player) obj;
      stmt.setString(1, player.getName());
      stmt.setString(2, player.getType());
      stmt.setLong(3, player.getId());
      stmt.executeUpdate();
      save(obj);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected abstract void save(DomainObject obj);

  @Override
  protected void insert(DomainObject obj) {
    try {
      PreparedStatement stmt = DB.prepareStatement("INSERT INTO sports_player VALUES (?, ?, ?)");
      Player player = (Player) obj;
      stmt.setLong(1, player.getId());
      stmt.setString(2, player.getName());
      stmt.setString(3, player.getType());
      stmt.executeUpdate();
      addRow(obj);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected abstract void addRow(DomainObject obj);
}
