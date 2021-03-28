package object_relation.structure.embeded_value;

import basic.money.Money;

public class Tester {
  public static void main(String[] args) {
    ProductOffering po1 = ProductOffering.find(1);
    System.out.println(po1.toString());

    ProductOffering po2 = ProductOffering.find(2);
    System.out.println(po2.toString());

    ProductOffering po3 = ProductOffering.find(3);
    System.out.println(po3.toString());

    po3.setBaseCost(po3.getBaseCost().add(Money.dollars(100)));
    po3.update();
    System.out.println(po3.toString());
  }
}
