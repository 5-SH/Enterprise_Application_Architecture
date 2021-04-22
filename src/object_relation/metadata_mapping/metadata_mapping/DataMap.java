package object_relation.metadata_mapping.metadata_mapping;

import java.util.ArrayList;
import java.util.List;

public class DataMap {
  private Class domainClass;
  private String tableName;
  private List columnMaps = new ArrayList();

  public DataMap(Class domainClass, String tableName) {
    this.domainClass = domainClass;
    this.tableName = tableName;
  }

  public void addColumn(String columnName, String type, String fieldName) {
    ColumnMap columnMap = new ColumnMap(columnName, fieldName, type, this);
    columnMaps.add(columnMap);
  }

  public Class getDomainClass() {
    return domainClass;
  }
}
