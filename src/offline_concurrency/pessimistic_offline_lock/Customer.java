package offline_concurrency.pessimistic_offline_lock;

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
      "id='" + getId() + '\'' + '\n' +
      "name='" + name + '\'' +
      '}';
  }
}
