package object_relation.metadata_mapping.metadata_mapping;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {
  static Map<String, Mapper> mappers = new HashMap<>();

  public void setMapper(String key, Mapper mapper) { mappers.put(key, mapper); }

  public static Mapper getMapper(String className) { return (Mapper) mappers.get(className); }
}
