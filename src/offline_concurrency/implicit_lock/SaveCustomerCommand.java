package offline_concurrency.implicit_lock;

public class SaveCustomerCommand extends BusinessTransactionCommand {
  @Override
  public void process() throws Exception {
    continueBusinessTransaction();
    Customer customer = (Customer) getReq().getSession().getAttribute("customer");
    String name = getReq().getParameter("customer_name");
    customer.setName(name);
    LockingMapper lockingMapper = (LockingMapper) MapperRegistry.getMapper("CustomerMapper");
    lockingMapper.update(customer);
    ExclusiveReadLockManagerDBImpl.INSTANCE.releaseLock(customer.getId(), AppSessionManager.getSession().getId());
    System.out.println("customer : " + customer.toString());

    // forward("/customerSaved.jsp");
  }
}
