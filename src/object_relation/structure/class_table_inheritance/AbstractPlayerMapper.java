package object_relation.structure.class_table_inheritance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractPlayerMapper extends Mapper {

  abstract protected String findStatement();

  @Override
  protected DomainObject find(long id) {
    DomainObject result = null;
    try {
      ResultSet rs = findRow(id);
      result = createDomainObject();
      load(result, rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  abstract protected DomainObject createDomainObject();
  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    super.load(obj, rs);
    String name = rs.getString("name");
    Player player = (Player) obj;
    player.setName(name);
  }

  public ResultSet findRow(long id) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = DB.prepareStatement(findStatement());
      stmt.setLong(1, id);
      rs = stmt.executeQuery();
      rs.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rs;
  }
}
