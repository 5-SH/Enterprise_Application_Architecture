package object_relation.structure.class_table_inheritance;

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
}
