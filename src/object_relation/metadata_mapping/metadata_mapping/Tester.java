package object_relation.metadata_mapping.metadata_mapping;

public class Tester {
  public static void main(String[] args) {
    PersonMapper personMapper = new PersonMapper();
    personMapper.loadDataMap();
    UnitOfWork.newCurrent();
    Person p1 = personMapper.find(1);
    System.out.println(p1.toString());

    p1.setFirstname("애린");
    p1.setLastname("주");
    p1.setNumberOfDependents(0);
    personMapper.update(p1);
  }
}
