package object_relation.structure.identity_field.compound_key;

public class LineItem extends DomainObjectWithKey {
  private long orderID;
  private long seq;
  private int amount;
  private String product;

  public LineItem(long orderID, long seq, int amount, String product) {
    super(new Key(orderID, seq));
    this.orderID = orderID;
    this.seq = seq;
    this.amount = amount;
    this.product = product;
  }
}
