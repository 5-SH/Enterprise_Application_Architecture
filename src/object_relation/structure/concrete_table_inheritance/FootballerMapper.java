package object_relation.structure.concrete_table_inheritance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FootballerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "F";
  private static final String TABLENAME = "concrete_footballer";

  @Override
  protected DomainObject find(long id) throws SQLException {
    return abstractFind(id, TABLENAME);
  }

  @Override
  protected DomainObject createDomainObject() {
    return new Footballer();
  }

  @Override
  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    super.load(obj, rs);
    Footballer footballer = (Footballer) obj;
    footballer.setClub(rs.getString("club"));
  }

  @Override
  protected String updateStatement() {
    return "UPDATE concrete_footballer SET name = ?, type = ?, club = ? WHERE id = ?";
  }

  protected void save(DomainObject obj, PreparedStatement stmt) throws SQLException {
    super.save(obj, stmt);
    Footballer footballer = (Footballer) obj;
    stmt.setString(3, footballer.getClub());
  }
}
