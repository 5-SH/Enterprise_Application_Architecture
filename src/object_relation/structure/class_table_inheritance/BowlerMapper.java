package object_relation.structure.class_table_inheritance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BowlerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "B";


  protected Bowler find(long id) {
    return (Bowler) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT id, name, bowling_average, type FROM athlete WHERE id = ?";
  }

  @Override
  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    super.load(obj, rs);
    Bowler bowler = (Bowler) obj;
    String bowlingAverage = rs.getString("bowling_average");
    bowler.setBowlingAverage(bowlingAverage);
  }

  @Override
  protected DomainObject createDomainObject() {
    return new Bowler();
  }

  @Override
  protected void save(DomainObject obj) {
    PreparedStatement stmt = null;
    try {
      Bowler bowler = (Bowler) obj;
      stmt = DB.prepareStatement("UPDATE athlete SET name = ?, bowling_average = ? WHERE id = ?");
      stmt.setLong(3, bowler.getId());
      stmt.setString(1, bowler.getName());
      stmt.setString(2, bowler.getBowlingAverage());
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
