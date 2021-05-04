package object_relation.metadata_mapping.metadata_mapping;

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
}
