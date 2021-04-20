package object_relation.structure.class_table_inheritance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FootballerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "F";
  private static final String TABLENAME = "footballer";

  @Override
  protected DomainObject find(long id) throws SQLException {
    return (Footballer) abstractFind(id, TABLENAME);
  }

  @Override
  protected DomainObject createDomainObject() {
    return new Footballer();
  }

  @Override
  protected void load(DomainObject obj) throws SQLException {
    super.load(obj);
    ResultSet rs = findRow(obj.getId(), TABLENAME);
    Footballer footballer = (Footballer) obj;
    footballer.setClub(rs.getString("club"));
  }

  @Override
  protected void save(DomainObject obj) {
    try {
      PreparedStatement stmt = DB.prepareStatement("UPDATE footballer SET club = ? WHERE id = ?");
      Footballer footballer = (Footballer) obj;
      stmt.setString(1, footballer.getClub());
      stmt.setLong(2, footballer.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void addRow(DomainObject obj) {
    try {
      PreparedStatement stmt = DB.prepareStatement("INSERT INTO footballer VALUES (?, ?)");
      Footballer footballer = (Footballer) obj;
      stmt.setLong(1, footballer.getId());
      stmt.setString(2, footballer.getClub());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
