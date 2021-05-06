package object_relation.metadata_mapping.repository;

import object_relation.metadata_mapping.metadata_mapping.DomainObject;
import object_relation.metadata_mapping.metadata_mapping.Person;
import object_relation.metadata_mapping.query_object.Criteria;

import java.util.HashSet;
import java.util.Set;

public class InMemoryStrategy implements RepositoryStrategy {
  private Set<DomainObject> domainObjects = new HashSet<>();

  public InMemoryStrategy() {
    domainObjects.add(new Person(1, "Bae", "신자", 0));
    domainObjects.add(new Person(2, "Bae", "치기", 2));
    domainObjects.add(new Person(3, "Hong", "익대", 100));
    domainObjects.add(new Person(4, "Sue", "뢰딩거", 1000));
  }

  @Override
  public Set matching(Class klass, Criteria aCriteria) {
    Set result = new HashSet();
    for (DomainObject obj : domainObjects) {
      if (aCriteria.isSatisfiedBy(obj)) {
        result.add(obj);
      }
    }
    return result;
  }
}
