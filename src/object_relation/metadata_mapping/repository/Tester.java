package object_relation.metadata_mapping.repository;

import object_relation.metadata_mapping.metadata_mapping.MapperRegistry;
import object_relation.metadata_mapping.metadata_mapping.Person;
import object_relation.metadata_mapping.metadata_mapping.PersonMapper;
import object_relation.metadata_mapping.metadata_mapping.UnitOfWork;

import java.util.Set;

public class Tester {
  public static void main(String[] args) {
    PersonRepository repository = new PersonRepository(new RelationalStrategy());

    PersonMapper personMapper = new PersonMapper();
    personMapper.loadDataMap();
    UnitOfWork.newCurrent();

    MapperRegistry.setMapper(Person.class.getName(), personMapper);

    Person p1 = personMapper.find(2);
    System.out.println(p1.toString());

    Set result1 = repository.dependentsOf(p1);
    System.out.println(result1.toString());

    repository.setStrategy(new InMemoryStrategy());
    Set result2 = repository.dependentsOf(p1);
    System.out.println(result2);
  }
}
