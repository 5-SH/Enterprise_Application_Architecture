package object_relation.metadata_mapping.query_object;

import object_relation.metadata_mapping.metadata_mapping.DataMap;

public class MatchCriteria extends Criteria {
  public MatchCriteria(String field, Object value) {
    super(null, field, value);
  }

  public String generateSql(DataMap dataMap) throws Exception {
    return "UPPER(" + dataMap.getColumnForField(field) + ") LIKE UPPER('" + value + "')";
  }
}
