package object_relation.metadata_mapping.metadata_mapping;

import java.util.Set;

public class Tester {
  public static void main(String[] args) {
    PersonMapper personMapper = new PersonMapper();
    personMapper.loadDataMap();
    UnitOfWork.newCurrent();
//    Person p1 = personMapper.find(2);
//    System.out.println(p1.toString());
//
//    p1.setFirstname("애린");
//    p1.setLastname("주");
//    p1.setNumberOfDependents(0);
//    personMapper.update(p1);

    Person np1 = new Person(6, "홍", "길동", 1000);
    personMapper.insert(np1);
//    personMapper.delete(p1);
    Set result1 = personMapper.findObjectWhere("lastname = '배'");
    System.out.println(result1.toString());
    Set result2 = personMapper.findLastNamesLike("배");
    System.out.println(result2.toString());
  }
}
