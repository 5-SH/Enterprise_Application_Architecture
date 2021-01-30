package data_source.data_mapper;

public class PersonEL extends DomainObjectEL {
  private String lastname;
  private String firstname;
  private int numberOfDependents;

  public void dbLoadLastname(String lastname) {
    assertStateIsLoading();
    this.lastname = lastname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setNumberOfDependents(int numberOfDependents) {
    this.numberOfDependents = numberOfDependents;
  }
}
