package offline_concurrency.implicit_lock;


import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {
  private static MapperRegistry soleInstance = new MapperRegistry();
  protected Map<String, Mapper> mappers;

  public MapperRegistry() {
    mappers = new HashMap<String, Mapper>();
    mappers.put("CustomerMapper", new LockingMapper(new CustomerMapper()));
  }

  private static MapperRegistry getInstance() { return soleInstance; }

  public static void initialize() { soleInstance = new MapperRegistry(); }

  public static Mapper getMapper(String name) {
    return getInstance().mappers.get(name);
  }
}
