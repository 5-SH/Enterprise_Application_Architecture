package object_relation.structure.concrete_table_inheritance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CricketerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "C";
  private static final String TABLENAME = "concrete_cricketer";

  @Override
  protected DomainObject find(long id) throws SQLException {
    return abstractFind(id, TABLENAME);
  }

  @Override
  protected DomainObject createDomainObject() {
    return new Cricketer();
  }

  @Override
  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    super.load(obj, rs);
    Cricketer cricketer = (Cricketer) obj;
    cricketer.setBattingAverage(rs.getString("batting_average"));
  }
}
