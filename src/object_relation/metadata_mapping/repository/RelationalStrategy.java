package object_relation.metadata_mapping.repository;

import object_relation.metadata_mapping.metadata_mapping.UnitOfWork;
import object_relation.metadata_mapping.query_object.Criteria;
import object_relation.metadata_mapping.query_object.QueryObject;

import java.util.Set;

public class RelationalStrategy implements RepositoryStrategy {
  @Override
  public Set matching(Class klass, Criteria aCriteria) {
    QueryObject query = new QueryObject(klass);
    query.addCriteria(aCriteria);
    UnitOfWork.newCurrent();
    return query.execute(UnitOfWork.getCurrent());
  }
}
