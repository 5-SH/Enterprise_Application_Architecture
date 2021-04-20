package object_relation.structure.concrete_table_inheritance;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractPlayerMapper extends Mapper {
  protected DomainObject abstractFind(long id, String tablename) throws SQLException {
    DomainObject result = (DomainObject) loadedMap.get(id);
    if (result != null) return result;

    ResultSet rs = findRow(id, tablename);
    if (rs == null) return null;

    result = createDomainObject();
    result.setId(rs.getLong("id"));
    load(result, rs);
    return result;
  }

  abstract protected DomainObject createDomainObject();
  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    Player player = (Player) obj;
    player.setName(rs.getString("name"));
    player.setType(rs.getString("type"));
  }

}
