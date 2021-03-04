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

  public LineItem(int amount, String product) {
    super(new Key(Long.valueOf(-1), Long.valueOf(-1)));
    this.orderID = Long.valueOf(-1);
    this.seq = Long.valueOf(-1);
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

  public int getAmount() {
    return amount;
  }

  public String getProduct() {
    return product;
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

  public void setKey(Key key) {
    super.setKey(key);
    this.orderID = key.longValue(0);
    this.seq = key.longValue(1);
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public void setProduct(String product) {
    this.product = product;
  }
}
