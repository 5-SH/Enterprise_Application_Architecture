package basic.plugin;

public class Customer extends DomainObject {
  private String name;

  private Customer(String name, Long id) {
    super(id);
    this.name = name;
  }

  public static Customer create(String name) {
    Long newObjId = IdGenerator.INSTANCE.nextId();
    Customer obj = new Customer(name, newObjId);
    return obj;
  }

  public String getName() {
    return name;
  }
}
