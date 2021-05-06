package object_relation.metadata_mapping.repository;

import object_relation.metadata_mapping.query_object.Criteria;

import java.util.Set;

public class Repository {
  RepositoryStrategy strategy;

  public Repository(RepositoryStrategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(RepositoryStrategy strategy) {
    this.strategy = strategy;
  }

  protected Set matching(Class klass, Criteria aCriteria) {
    return strategy.matching(klass, aCriteria);
  }

}
