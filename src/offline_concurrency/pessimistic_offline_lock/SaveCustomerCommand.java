package offline_concurrency.pessimistic_offline_lock;

public class SaveCustomerCommand extends BusinessTransactionCommand {
  @Override
  public void process() throws Exception {
    continueBusinessTransaction();
    Customer customer = (Customer) getReq().getSession().getAttribute("customer");
    String name = getReq().getParameter("customer_name");
    customer.setName(name);
    CustomerMapper customerMapper = (CustomerMapper) MapperRegistry.getMapper("CustomerMapper");
    customerMapper.update(customer);
    ExclusiveReadLockManagerDBImpl.INSTANCE.releaseLock(customer.getId(), AppSessionManager.getSession().getId());
    System.out.println("customer : " + customer.toString());

    // forward("/customerSaved.jsp");
  }
}
