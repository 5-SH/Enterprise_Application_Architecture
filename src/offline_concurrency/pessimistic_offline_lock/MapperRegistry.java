package offline_concurrency.pessimistic_offline_lock;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {
  private static MapperRegistry soleInstance = new MapperRegistry();
  protected Map<String, AbstractMapper> mappers;

  public MapperRegistry() {
    mappers = new HashMap<String, AbstractMapper>();
    mappers.put("CustomerMapper", new CustomerMapper());
  }

  private static MapperRegistry getInstance() { return soleInstance; }

  public static void initialize() { soleInstance = new MapperRegistry(); }

  public static AbstractMapper getMapper(String name) {
    return getInstance().mappers.get(name);
  }
}
