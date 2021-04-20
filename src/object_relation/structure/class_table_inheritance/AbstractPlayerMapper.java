package object_relation.structure.class_table_inheritance;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractPlayerMapper extends Mapper {
  public static final String TABLENAME = "sports_player";
  abstract protected DomainObject createDomainObject();

  protected DomainObject abstractFind(long id, String tablename) throws SQLException {
    DomainObject result = (DomainObject) loadedMap.get(id);
    if (result != null) return result;

    ResultSet rs = findRow(id, tablename);
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

}
