package offline_concurrency.coarse_grained_lock;

public class Tester {
  public static void main(String[] args) {
    Customer c1 = Customer.create("승호");
    c1.addAddress("행운동", "관악구", "서울시");
    c1.addAddress("좌동", "해운대구", "부산시");

    MapperRegistry.getMapper("CustomerMapper").insert(c1);
  }
}
