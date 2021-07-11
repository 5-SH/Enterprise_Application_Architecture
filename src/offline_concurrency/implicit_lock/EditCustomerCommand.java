package offline_concurrency.implicit_lock;

public class EditCustomerCommand extends BusinessTransactionCommand {
  @Override
  public void process() throws Exception {
    startNewBusinessTransaction();
    Long customerId = new Long(getReq().getParameter("customer_id"));
    LockingMapper lockingMapper = (LockingMapper) MapperRegistry.getMapper("CustomerMapper");
    Customer customer = (Customer) lockingMapper.find(customerId);
    getReq().getSession().setAttribute("customer", customer);

    System.out.println("customer : " + customer.toString());
  }
}
