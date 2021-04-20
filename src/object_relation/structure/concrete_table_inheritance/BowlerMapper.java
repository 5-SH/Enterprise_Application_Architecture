package object_relation.structure.concrete_table_inheritance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BowlerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "B";
  private static final String TABLENAME = "concrete_bowler";

  @Override
  protected DomainObject find(long id) throws SQLException {
    return abstractFind(id, TABLENAME);
  }

  @Override
  protected DomainObject createDomainObject() {
    return new Bowler();
  }

  @Override
  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    super.load(obj, rs);
    Bowler bowler = (Bowler) obj;
    bowler.setBowlingAverage(rs.getString("bowling_average"));
  }

  @Override
  protected String updateStatement() {
    return "UPDATE concrete_bowler SET name = ?, type = ?, bowling_average = ? WHERE id = ?";
  }

  protected void save(DomainObject obj, PreparedStatement stmt) throws SQLException {
    super.save(obj, stmt);
    Bowler bowler = (Bowler) obj;
    stmt.setString(3, bowler.getBowlingAverage());
  }
}
