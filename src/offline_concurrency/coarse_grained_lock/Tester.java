package offline_concurrency.coarse_grained_lock;

public class Tester {
  public static void main(String[] args) {
    Customer c1 = Customer.create("승호");
    c1.addAddress("행운동", "관악구", "서울시");
    c1.addAddress("좌동", "해운대구", "부산시");

    MapperRegistry.getMapper("CustomerMapper").insert(c1);

    c1.setName("수정된승호");
    Address addr1 = (Address) c1.getAddressList().get(0);
    addr1.setState("서울시");
    addr1.setCity("동작구");
    addr1.setLine1("장승배기");

    MapperRegistry.getMapper("CustomerMapper").update(c1);

    MapperRegistry.getMapper("CustomerMapper").delete(c1);
  }
}
