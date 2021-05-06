package object_relation.metadata_mapping.query_object;

import object_relation.metadata_mapping.metadata_mapping.DataMap;

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

  public Criteria(String sqlOperator, String field, Object value) {
    this.sqlOperator = sqlOperator;
    this.field = field;
    this.value = value;
  }

  public String generateSql(DataMap dataMap) throws Exception {
    return dataMap.getColumnForField(field) + sqlOperator + value;
  }
}
