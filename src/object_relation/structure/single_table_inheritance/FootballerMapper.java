package object_relation.structure.single_table_inheritance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FootballerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "F";

  protected Footballer find(long id) {
    return (Footballer) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT id, name, club, type FROM athlete WHERE id = ?";
  }

  @Override
  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    super.load(obj, rs);
    Footballer footballer = (Footballer) obj;
    String club = rs.getString("club");
    footballer.setClub(club);
  }

  @Override
  protected DomainObject createDomainObject() { return new Footballer(); }

  @Override
  protected String updateStatement() {
    return "UPDATE athlete SET name = ?, club = ? WHERE id = ?";
  }

  @Override
  protected void save(DomainObject obj, PreparedStatement stmt) {
    try {
      Footballer footballer = (Footballer) obj;
      stmt.setString(1, footballer.getName());
      stmt.setString(2, footballer.getClub());
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected String insertStatement() {
    return "INSERT INTO athlete (name, club, id, type) VALUES (?, ?, ?, ?)";
  }
}
