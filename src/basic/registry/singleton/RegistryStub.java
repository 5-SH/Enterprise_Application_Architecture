package basic.registry.singleton;

public class RegistryStub extends Registry {
  public RegistryStub() {
    super.personFinder = new PersonFinderStub();
  }
}
