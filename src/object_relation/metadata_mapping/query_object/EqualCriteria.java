package object_relation.metadata_mapping.query_object;

import object_relation.metadata_mapping.metadata_mapping.DataMap;

public class EqualCriteria extends Criteria {
  public EqualCriteria(String field, Object value) {
    super(null, field, value);
  }

  public String generateSql(DataMap dataMap) throws Exception {
    return dataMap.getColumnForField(field) + "='" + value + "'";
  }
}
