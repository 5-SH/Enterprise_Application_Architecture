package object_relation.structure.class_table_inheritance;

import java.sql.ResultSet;

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
  protected void load(DomainObject obj, ResultSet rs) {

  }
}
