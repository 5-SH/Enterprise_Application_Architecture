package basic.registry.threadlocal;

import basic.registry.PersonFinder;

public class ThreadLocalRegistry {
  private static ThreadLocal instances = new ThreadLocal();
  private PersonFinder personFinder = new PersonFinder();

  public static PersonFinder personFinder() {
    return getInstance().personFinder;
  }
  public static ThreadLocalRegistry getInstance() {
    return (ThreadLocalRegistry) instances.get();
  }

  public static void begin() {
    assert instances.get() != null : "ThreadLocal is not null";
    instances.set(new ThreadLocalRegistry());
  }

  public static void end() {
    assert getInstance() == null : "ThreadLocal is null";
    instances.set(null);
  }
}
