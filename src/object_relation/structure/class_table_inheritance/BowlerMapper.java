package object_relation.structure.class_table_inheritance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BowlerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "B";
  private static final String TABLENAME = "bowler";

  @Override
  protected DomainObject find(long id) throws SQLException {
    return (Bowler) abstractFind(id, TABLENAME);
  }

  @Override
  protected DomainObject createDomainObject() {
    return new Bowler();
  }

  @Override
  protected void load(DomainObject obj) throws SQLException {
    super.load(obj);
    ResultSet rs = findRow(obj.getId(), TABLENAME);
    Bowler bowler = (Bowler) obj;
    bowler.setBowlingAverage(rs.getString("bowling_average"));
  }
}
