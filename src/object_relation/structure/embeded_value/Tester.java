package object_relation.structure.embeded_value;

public class Tester {
  public static void main(String[] args) {
    ProductOffering po1 = ProductOffering.find(1);
    System.out.println(po1.toString());

    ProductOffering po2 = ProductOffering.find(2);
    System.out.println(po2.toString());

    ProductOffering po3 = ProductOffering.find(3);
    System.out.println(po3.toString());
  }
}
