package object_relation.structure.class_table_inheritance;

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


}
