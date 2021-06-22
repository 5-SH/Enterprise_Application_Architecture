package offline_concurrency.optimistic_offline_lock;

public class Customer extends DomainObject {
  private String name;

  public Customer(Long id, String name) {
    super(id);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Customer{" +
      "name='" + name + '\'' + ", " +
      "modifiedby='" + super.getModifiedBy() + '\'' + ", " +
      "modified='" + super.getModified() + '\'' + ", " +
      "version='" + super.getVersion() + '\'' + ", " +
      '}';
  }
}
