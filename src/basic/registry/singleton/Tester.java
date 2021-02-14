package basic.registry.singleton;

public class Tester {
  public static void main(String[] args) {
    Registry.initializeStub();
    PersonFinder finder = Registry.personFinder();
    Person person = finder.find(new Long(1));

    System.out.println(person.getLastname() + " " +
      person.getFirstname() +
      " / number of dependents : " +
      person.getNumberOfDependents());
  }
}
