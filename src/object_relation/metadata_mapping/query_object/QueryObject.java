package object_relation.metadata_mapping.query_object;

import object_relation.metadata_mapping.metadata_mapping.MapperRegistry;
import object_relation.metadata_mapping.metadata_mapping.UnitOfWork;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QueryObject {
  private Class klass;
  private List<Criteria> criteria = new ArrayList();

  public QueryObject(Class klass) {
    this.klass = klass;
  }

  public void addCriteria(Criteria criteria) {
    this.criteria.add(criteria);
  }

  public Set execute(UnitOfWork uow) {
    return MapperRegistry.getMapper(klass.getName()).findObjectWhere(generateWhereClause());
  }

  private String generateWhereClause() {
    StringBuffer result = new StringBuffer();
    try {
      for (Criteria criteria : criteria) {
        if (result.length() != 0) {
          result.append(" AND ");
        }
        result.append(criteria.generateSql(MapperRegistry.getMapper(klass.getName()).getDataMap()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result.toString();
  }
}
