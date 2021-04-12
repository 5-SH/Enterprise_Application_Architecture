package object_relation.structure.class_table_inheritance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CricketerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "C";

  public Cricketer find(long id) {
    return (Cricketer) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT id, name, batting_average, type FROM athlete WHERE id = ?";
  }

  @Override
  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    super.load(obj, rs);
    Cricketer cricketer = (Cricketer) obj;
    String battingAverage = rs.getString("batting_average");
    cricketer.setBattingAverage(battingAverage);
  }

  @Override
  protected DomainObject createDomainObject() {
    return new Cricketer();
  }

  @Override
  protected void save(DomainObject obj) {
    PreparedStatement stmt = null;
    try {
      Cricketer cricketer = (Cricketer) obj;
      stmt = DB.prepareStatement("UPDATE athlete SET name = ?, batting_average = ? WHERE id = ?");
      stmt.setLong(3, cricketer.getId());
      stmt.setString(1, cricketer.getName());
      stmt.setString(2, cricketer.getBattingAverage());
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
