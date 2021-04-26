package object_relation.metadata_mapping.metadata_mapping;

public class Person extends DomainObject {
  private String lastname;
  private String firstname;
  private int numberOfDependents;

  public Person() {
  }

  public Person(String lastname, String firstname, int numberOfDependents) {
    this.lastname = lastname;
    this.firstname = firstname;
    this.numberOfDependents = numberOfDependents;
  }

  public Person(long id, String lastname, String firstname, int numberOfDependents) {
    super(id);
    this.lastname = lastname;
    this.firstname = firstname;
    this.numberOfDependents = numberOfDependents;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
    markDirty();
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
    markDirty();
  }

  public int getNumberOfDependents() {
    return numberOfDependents;
  }

  public void setNumberOfDependents(int numberOfDependents) {
    this.numberOfDependents = numberOfDependents;
    markDirty();
  }

  @Override
  public String toString() {
    return "Person{" +
      "id='" + super.getId() + '\'' +
      ", lastname='" + lastname + '\'' +
      ", firstname='" + firstname + '\'' +
      ", numberOfDependents=" + numberOfDependents +
      '}';
  }
}
