package basic.plugin;

public class TestIdGenerator implements IdGenerator {
  private long count = 0;

  public synchronized Long nextId() {
    return new Long(count++);
  }
}
