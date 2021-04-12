package object_relation.structure.class_table_inheritance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractPlayerMapper extends Mapper {

  abstract protected String findStatement();
  abstract protected DomainObject createDomainObject();

  protected DomainObject abstractFind(long id) {
    DomainObject result = (DomainObject) loadedMap.get(id);
    if (result != null) return result;

    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = DB.prepareStatement(findStatement());
      stmt.setLong(1, id);
      rs = stmt.executeQuery();
      rs.next();
      result = createDomainObject();
      load(result, rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    super.load(obj, rs);
    Player player = (Player) obj;
    String name = rs.getString("name");
    player.setName(name);
    String type = rs.getString("type");
    player.setType(type);
  }

  protected void update(DomainObject obj) {
    save(obj);
  }

  protected abstract void save(DomainObject obj);
}
