package basic.registry.singleton;

import basic.registry.Person;
import basic.registry.PersonFinder;

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
