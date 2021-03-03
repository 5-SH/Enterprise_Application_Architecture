package object_relation.structure.identity_field.compound_key;

import java.util.Objects;

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

  public LineItem(Key key, int amount, String product) {
    super(key);
    this.orderID = key.longValue(0);
    this.seq = key.longValue(1);
    this.amount = amount;
    this.product = product;
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderID, seq);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof LineItem)) return false;

    LineItem other = (LineItem) obj;
    if (this.orderID == other.getOrderID() &&
      this.seq == other.getSeq()) {
      return true;
    } else {
      return false;
    }
  }

  public long getOrderID() {
    return orderID;
  }

  public long getSeq() {
    return seq;
  }

  @Override
  public String toString() {
    return "LineItem{" +
      "orderID=" + orderID +
      ", seq=" + seq +
      ", amount=" + amount +
      ", product='" + product + '\'' +
      '}';
  }
}
