package basic.registry.singleton;

public class PersonFinderStub extends PersonFinder {
  public Person find(Long id) {
    if (id == 1) {
      return new Person("Fowler", "Martin", 10);
    }
    throw new IllegalArgumentException("Can't find id: " + String.valueOf(id));
  }
}
