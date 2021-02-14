package basic.registry;

public class Person extends DomainObject {
  private String lastname;
  private String firstname;
  private int numberOfDependents;

  public Person(String lastname, String firstname, int numberOfDependents) {
    this.lastname = lastname;
    this.firstname = firstname;
    this.numberOfDependents = numberOfDependents;
  }

  public Person(Long id, String lastname, String firstname, int numberOfDependents) {
    super(id);
    this.lastname = lastname;
    this.firstname = firstname;
    this.numberOfDependents = numberOfDependents;
  }

  public String getLastname() {
    return lastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public int getNumberOfDependents() {
    return numberOfDependents;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  @Override
  public String toString() {
    return "Person{" +
      "lastname='" + lastname + '\'' +
      ", firstname='" + firstname + '\'' +
      ", numberOfDependents=" + numberOfDependents +
      '}';
  }
}
