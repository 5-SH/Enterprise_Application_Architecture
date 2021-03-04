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

    Order newOrder = new Order("주애린");
    orderMapper.insert(newOrder);

    LineItemMapper lineItemMapper = MapperRegistry.lineItem();
    LineItem newLineItem1 = new LineItem(100, "노트북");
    Key key = lineItemMapper.insert(newLineItem1, newOrder);

    LineItem newLineItem2 = new LineItem(80, "태블릿");
    lineItemMapper.insert(newLineItem2, newOrder);

    LineItem newLineItem3 = new LineItem(10, "무선마우스");
    lineItemMapper.insert(newLineItem3, newOrder);

    for (int i = 0; i < newOrder.getLineItemList().size(); i++) {
      System.out.println(i + 1 + ": " + newOrder.getLineItemList().get(i).toString());
    }
  }
}
