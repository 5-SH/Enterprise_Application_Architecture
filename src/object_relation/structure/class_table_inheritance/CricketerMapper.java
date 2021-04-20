package object_relation.structure.class_table_inheritance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CricketerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "C";
  private static final String TABLENAME = "cricketer";

  @Override
  protected DomainObject find(long id) throws SQLException {
    return (Cricketer) abstractFind(id, TABLENAME);
  }

  @Override
  protected DomainObject createDomainObject() {
    return new Cricketer();
  }

  @Override
  protected void load(DomainObject obj) throws SQLException {
    super.load(obj);
    ResultSet rs = findRow(obj.getId(), TABLENAME);
    Cricketer cricketer = (Cricketer) obj;
    cricketer.setBattingAverage(rs.getString("batting_average"));
  }

  @Override
  protected void save(DomainObject obj) {
    try {
      PreparedStatement stmt = DB.prepareStatement("UPDATE cricketer SET batting_average = ? WHERE id = ?");
      Cricketer cricketer = (Cricketer) obj;
      stmt.setString(1, cricketer.getBattingAverage());
      stmt.setLong(2, cricketer.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
