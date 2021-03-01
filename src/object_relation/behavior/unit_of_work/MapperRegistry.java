package object_relation.behavior.unit_of_work;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {
  static Map<String, Mapper> mappers = new HashMap<>();

  public void setMapper(String key, Mapper mapper) {
    mappers.put(key, mapper);
  }

  public static Mapper getMapper(String className) {
    return (Mapper) mappers.get(className);
  }
}
