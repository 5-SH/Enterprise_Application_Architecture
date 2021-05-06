package object_relation.metadata_mapping.metadata_mapping;

import java.lang.reflect.Field;

public class ColumnMap {
  private String columnName;
  private String fieldName;
  private String type;
  private Field field;
  private DataMap dataMap;

  public ColumnMap(String columnName, String fieldName, String type, DataMap dataMap) {
    this.columnName = columnName;
    this.fieldName = fieldName;
    this.type = type;
    this.dataMap = dataMap;
    initField();
  }

  private void initField() {
    try {
      field = dataMap.getDomainClass().getDeclaredField(fieldName);
      field.setAccessible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getColumnName() {
    return columnName;
  }

  public void setField(Object result, Object columnValue) {
    try {
      field.set(result, columnValue);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object getValue(Object subject) {
    try {
      return field.get(subject);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // QueryObject code

  public String getFieldName() {
    return fieldName;
  }
}
