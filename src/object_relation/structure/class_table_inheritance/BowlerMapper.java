package object_relation.structure.class_table_inheritance;

import java.sql.PreparedStatement;
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

  @Override
  protected void save(DomainObject obj) {
    try {
      PreparedStatement stmt = DB.prepareStatement("UPDATE bowler SET bowling_average = ? WHERE id = ?");
      Bowler bowler = (Bowler) obj;
      stmt.setString(1, bowler.getBowlingAverage());
      stmt.setLong(2, bowler.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void addRow(DomainObject obj) {
    try {
      PreparedStatement stmt = DB.prepareStatement("INSERT INTO bowler VALUES (?, ?)");
      Bowler bowler = (Bowler) obj;
      stmt.setLong(1, bowler.getId());
      stmt.setString(2, bowler.getBowlingAverage());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
