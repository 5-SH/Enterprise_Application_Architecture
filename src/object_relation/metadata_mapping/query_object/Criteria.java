package object_relation.metadata_mapping.query_object;

import object_relation.metadata_mapping.metadata_mapping.DataMap;
import object_relation.metadata_mapping.metadata_mapping.DomainObject;
import object_relation.metadata_mapping.metadata_mapping.Person;

import java.lang.reflect.Field;

public class Criteria {
  private String sqlOperator;
  protected String field;
  protected Object value;

  public static Criteria greaterThan(String field, int value) {
    return greaterThan(field, Integer.valueOf(value));
  }

  public static Criteria greaterThan(String field, Object value) {
    return new Criteria(">", field, value);
  }

  public static Criteria matches(String field, String pattern) {
    return new MatchCriteria(field, pattern);
  }

  public static Criteria equal(String field, String pattern) {
    return new EqualCriteria(field, pattern);
  }

  protected Criteria(String sqlOperator, String field, Object value) {
    this.sqlOperator = sqlOperator;
    this.field = field;
    this.value = value;
  }

  public String generateSql(DataMap dataMap) throws Exception {
    return dataMap.getColumnForField(field) + sqlOperator + value;
  }

  public boolean isSatisfiedBy(DomainObject obj) {
    try {
      Field f = obj.getClass().getDeclaredField(field);
      f.setAccessible(true);
      Object v = f.get(obj);
      if (v.equals(value)) return  true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
