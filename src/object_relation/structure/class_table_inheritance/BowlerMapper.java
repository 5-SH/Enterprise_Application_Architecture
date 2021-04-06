package object_relation.structure.class_table_inheritance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BowlerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "B";

  @Override
  protected String findStatement() {
    return null;
  }

  @Override
  protected DomainObject createDomainObject() {
    return null;
  }

  @Override
  protected DomainObject find(long id) throws SQLException {
    return null;
  }

  @Override
  protected void load(DomainObject obj, ResultSet rs) {

  }
}
