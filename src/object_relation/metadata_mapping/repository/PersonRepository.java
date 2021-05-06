package object_relation.metadata_mapping.repository;

import object_relation.metadata_mapping.metadata_mapping.Person;
import object_relation.metadata_mapping.query_object.Criteria;

import java.util.Set;

public class PersonRepository extends Repository {
  public PersonRepository(RepositoryStrategy strategy) {
    super(strategy);
  }

  public Set dependentsOf(Person aPerson) {
   return matching(Person.class, Criteria.equal("lastname", aPerson.getLastname()));
 }
}
