package object_relation.metadata_mapping.repository;

import object_relation.metadata_mapping.query_object.Criteria;

import java.util.List;
import java.util.Set;

public interface RepositoryStrategy {
  Set matching(Class klass, Criteria aCriteria);
}
