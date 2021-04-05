package object_relation.structure.class_table_inheritance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CricketerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "C";

  public Cricketer find(long id) {
    return (Cricketer) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT id, name, batting_average FROM athlete WHERE id = ?";
  }

  @Override
  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    super.load(obj, rs);
    String battingAverage = rs.getString("batting_average");
    Cricketer cricketer = (Cricketer) obj;
    cricketer.setBattingAverage(battingAverage);
  }

  @Override
  protected DomainObject createDomainObject() {
    return new Cricketer();
  }
}
