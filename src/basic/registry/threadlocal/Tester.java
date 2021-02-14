package basic.registry.threadlocal;

import basic.registry.Person;
import basic.registry.PersonFinder;
import basic.registry.singleton.Registry;

public class Tester {
  public static void main(String[] args) {
    RegistryThread registry1 = new RegistryThread();
    RegistryThread registry2 = new RegistryThread();

    Thread thread1 = new Thread(registry1, "승호 오");
    Thread thread2 = new Thread(registry2, "애린 주");

    thread1.start();
    thread2.start();
  }
}

class RegistryThread implements Runnable {
  @Override
  public void run() {
    try {
      ThreadLocalRegistry.begin();
      PersonFinder finder = ThreadLocalRegistry.personFinder();
      Person person = finder.find(new Long(1));

      if (person == null) {
        String fullName = Thread.currentThread().getName();
        String[] split = fullName.split(" ");
        String firstName = split[0];
        String lastName = split[1];

        ThreadLocalRegistry.personFinder().load(
          new Long(1),
          new Person(firstName, lastName, 0)
        );
      }

      person = finder.find(new Long(1));
      System.out.println(person.getLastname() + " " +
        person.getFirstname() +
        " / number of dependents : " +
        person.getNumberOfDependents());
    } finally {
      ThreadLocalRegistry.end();
    }
  }
}
