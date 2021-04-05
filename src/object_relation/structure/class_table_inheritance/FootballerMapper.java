package object_relation.structure.class_table_inheritance;

import java.sql.ResultSet;

public class FootballerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "S";

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
