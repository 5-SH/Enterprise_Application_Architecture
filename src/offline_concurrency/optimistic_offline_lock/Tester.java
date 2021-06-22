package offline_concurrency.optimistic_offline_lock;

import java.util.concurrent.TimeUnit;

public class Tester {
  public static void main(String[] args) throws InterruptedException {
    CustomerMapper customerMapper = new CustomerMapper("customer", new String[]{ "id", "name" });
    Customer newCustomer = new Customer(new Long(1), "오승호");
    customerMapper.insert(newCustomer);

    // DB 에서 version 수정하기 위해 delay -> 잠금 예외 발생
    TimeUnit.SECONDS.sleep(10);

    System.out.println("수정");
    newCustomer.setName("주애린");
    customerMapper.update(newCustomer);

    Customer c1 = (Customer) customerMapper.find(new Long(1));

//    System.out.println("삭제");
//    customerMapper.delete(c1);
//    customerMapper.delete(c1);
  }
}
