package object_relation.metadata_mapping.metadata_mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

public class PersonMapper extends Mapper {
  public void loadDataMap() {
    dataMap = new DataMap(Person.class, "person");
    dataMap.addColumn("lastname", "varchar", "lastname");
    dataMap.addColumn("firstname", "varchar", "firstname");
    dataMap.addColumn("numberOfDependents", "int", "numberOfDependents");
  }

  public Person find(long id) {
      return (Person) findObject(id);
  }

  public Set findLastNamesLike(String pattern) {
    String sql = "SELECT " + dataMap.columnList() + " FROM " + dataMap.getTableName() + " WHERE UPPER(lastname) like UPPER(?)";
    Set result = null;
    try {
      PreparedStatement stmt = db.prepareStatement(sql);
      stmt.setString(1, pattern);
      ResultSet rs = stmt.executeQuery();
      result = loadAll(rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
