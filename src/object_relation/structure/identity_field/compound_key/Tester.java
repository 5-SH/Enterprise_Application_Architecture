package object_relation.structure.identity_field.compound_key;

public class Tester {
  public static void main(String[] args) {
    OrderMapper orderMapper = MapperRegistry.order();
    Order order = orderMapper.find(Long.valueOf(1));

    System.out.println("ID: " + order.getID() +
                        " / " +
                        "Customer: " + order.getCustomer());

    for (int i = 0; i < order.getLineItemList().size(); i++) {
      System.out.println(i + 1 + ": " + order.getLineItemList().get(i).toString());
    }

    order = orderMapper.find(Long.valueOf(1));

    order.addLineItem(new LineItem(1, 1, 10, "키보드"));
//    order.addLineItem(new LineItem(1, 4, 1, "볼펜"));
    for (int i = 0; i < order.getLineItemList().size(); i++) {
      System.out.println(i + 1 + ": " + order.getLineItemList().get(i).toString());
    }
  }
}
