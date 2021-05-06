package object_relation.metadata_mapping.query_object;

import object_relation.metadata_mapping.metadata_mapping.MapperRegistry;
import object_relation.metadata_mapping.metadata_mapping.Person;
import object_relation.metadata_mapping.metadata_mapping.PersonMapper;
import object_relation.metadata_mapping.metadata_mapping.UnitOfWork;

import java.util.Set;

public class Tester {
  public static void main(String[] args) {
    PersonMapper personMapper = new PersonMapper();
    personMapper.loadDataMap();
    MapperRegistry.setMapper(Person.class.getName(), personMapper);

    QueryObject query = new QueryObject(Person.class);
    query.addCriteria(Criteria.greaterThan("numberOfDependents", 1));
    query.addCriteria(Criteria.matches("lastname", "Hong"));

    UnitOfWork.newCurrent();
    Set result = query.execute(UnitOfWork.getCurrent());
    System.out.println(result.toString());
  }
}
