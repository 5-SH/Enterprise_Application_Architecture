package offline_concurrency.coarse_grained_lock.root_optimistic;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {
  private static MapperRegistry soleInstance = new MapperRegistry();
  protected Map<String, Mapper> mappers;

  public MapperRegistry() {
    mappers = new HashMap<>();
    mappers.put("offline_concurrency.coarse_grained_lock.root_optimistic.Customer", new CustomerMapper());
    mappers.put("offline_concurrency.coarse_grained_lock.root_optimistic.Address", new AddressMapper());
  }

  private static MapperRegistry getInstance() { return soleInstance; }

  public static void initialize() { soleInstance = new MapperRegistry(); }

  public static Mapper getMapper(String name) { return getInstance().mappers.get(name); }
}
