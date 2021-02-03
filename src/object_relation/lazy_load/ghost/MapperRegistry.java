package object_relation.lazy_load.ghost;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry implements DataSource.DataSourceInterface {
  static Map<String, Mapper> mappers = new HashMap<>();

  @Override
  public void load(DomainObject obj) {
    mapper(obj.getClass().getName()).load(obj);
  }

  public void setMapper(String key, Mapper mapper) {
    mappers.put(key, mapper);
  }

  public static Mapper mapper(String className) {
    return (Mapper) mappers.get(className);
  }
}
