package offline_concurrency.coarse_grained_lock.pessimistic;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {
  private static MapperRegistry soleInstance = new MapperRegistry();
  protected Map<String, AbstractMapper> mappers;

  public MapperRegistry() {
    mappers = new HashMap<String, AbstractMapper>();
    mappers.put("CustomerMapper", new CustomerMapper());
    mappers.put("AddressMapper", new AddressMapper());
  }

  private static MapperRegistry getInstance() { return soleInstance; }

  public static void initialize() { soleInstance = new MapperRegistry(); }

  public static AbstractMapper getMapper(String name) {
    return getInstance().mappers.get(name);
  }
}
