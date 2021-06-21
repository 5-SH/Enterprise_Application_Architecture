package offline_concurrency.optimistic_offline_lock;

public class Tester {
  public static void main(String[] args) {
    CustomerMapper customerMapper = new CustomerMapper("customer", new String[]{ "id", "createdby",  "created" });
    Customer c1 = (Customer) customerMapper.find(new Long(1));
    System.out.println(c1);

    customerMapper.delete(c1);
    customerMapper.delete(c1);
  }
}
