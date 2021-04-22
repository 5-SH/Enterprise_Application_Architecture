package object_relation.metadata_mapping.metadata_mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper extends Mapper {
  DataMap dataMap;

  protected void loadDataMap() {
    dataMap = new DataMap(Person.class, "person");
    dataMap.addColumn("lastname", "varchar", "lastName");
    dataMap.addColumn("firstname", "varchar", "firstName");
    dataMap.addColumn("numberOfDependents", "int", "numberOfDependents");
  }

  @Override
  protected String findStatement() {
    return null;
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    return null;
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
