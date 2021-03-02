package object_relation.structure.identity_field.compound_key;

public class Order extends DomainObjectWithKey {
  private long ID;
  private String customer;

  public Order(long ID, String customer) {
    super(new Key(ID));
    this.ID = ID;
    this.customer = customer;
  }
}
