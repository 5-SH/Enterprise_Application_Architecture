package object_relation.structure.identity_field.compound_key;

import java.util.ArrayList;
import java.util.List;

public class Order extends DomainObjectWithKey {
  private long ID;
  private String customer;
  private List<LineItem> lineItemList = new ArrayList<LineItem>();;

  public Order(String customer) {
    super(new Key(-1));
    this.customer = customer;
  }

  public Order(Key key, String customer) {
    super(key);
    this.ID = key.longValue();
    this.customer = customer;
  }

  public void addLineItem(LineItem lineItem) {
    System.out.println("lineItemList contains?: " + lineItemList.contains(lineItem));
    if (!lineItemList.contains(lineItem)) {
      lineItemList.add(lineItem);
    }
  }

  public List<LineItem> getLineItemList() {
    return lineItemList;
  }

  public long getID() {
    return ID;
  }

  public String getCustomer() {
    return customer;
  }

  public void setKey(Key key) {
    super.setKey(key);
    this.ID = key.longValue();
  }
}
