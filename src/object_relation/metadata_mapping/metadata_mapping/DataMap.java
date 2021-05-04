package object_relation.metadata_mapping.metadata_mapping;

import java.util.ArrayList;
import java.util.List;

public class DataMap {
  private Class domainClass;
  private String tableName;
  private List<ColumnMap> columnMaps = new ArrayList();

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

  public String columnList() {
    StringBuffer result = new StringBuffer("ID");
    for (ColumnMap columnMap : columnMaps) {
      result.append(", ");
      result.append(columnMap.getColumnName());
    }
    return result.toString();
  }

  public String getTableName() {
    return tableName;
  }

  public List<ColumnMap> getColumnMaps() {
    return columnMaps;
  }

  public String updateList() {
    StringBuffer result = new StringBuffer(" SET ");
    for (ColumnMap columnMap : columnMaps) {
      result.append(columnMap.getColumnName());
      result.append(" = ?,");
    }
    result.setLength(result.length() - 1);
    return result.toString();
  }

  public String insertList() {
    StringBuffer result = new StringBuffer();
    for (ColumnMap columnMap : columnMaps) {
      result.append(", ");
      result.append("?");
    }
    return result.toString();
  }

}
