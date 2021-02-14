package basic.plugin;

public class Tester {
  public static void main(String[] args) {
    Customer customer1 = Customer.create("test1");
    System.out.println("New customer name: " + customer1.getName() + " id: " + customer1.getId());

    Customer customer2 = Customer.create("test2");
    System.out.println("New customer name: " + customer2.getName() + " id: " + customer2.getId());

    Customer customer3 = Customer.create("test3");
    System.out.println("New customer name: " + customer3.getName() + " id: " + customer3.getId());
  }
}
