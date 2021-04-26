package object_relation.metadata_mapping.metadata_mapping;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonMapper extends Mapper {
  protected void loadDataMap() {
    dataMap = new DataMap(Person.class, "person");
    dataMap.addColumn("lastname", "varchar", "lastname");
    dataMap.addColumn("firstname", "varchar", "firstname");
    dataMap.addColumn("numberOfDependents", "int", "numberOfDependents");
  }

  public Person find(long id) {
      return (Person) findObject(id);
  }

  @Override
  protected String insertStatement() {
    return null;
  }

  @Override
  protected void doInsert(DomainObject obj, PreparedStatement stmt) throws SQLException {

  }

  @Override
  protected String updateStatement() {
    return null;
  }

  @Override
  protected void doUpdate(DomainObject obj, PreparedStatement stmt) throws SQLException {

  }

  @Override
  protected String deleteStatement() {
    return null;
  }
}
