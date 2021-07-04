package offline_concurrency.coarse_grained_lock.pessimistic;

public class LoadCustomerCommand extends BusinessTransactionCommand {
  @Override
  public void process() throws Exception {
    startNewBusinessTransaction();
    Long customerId = new Long(getReq().getParameter("customer_id"));
    CustomerMapper customerMapper = (CustomerMapper) MapperRegistry.getMapper("CustomerMapper");
    Customer customer = customerMapper.find(customerId);

    Version version = ExclusiveReadLockManagerDBImpl.INSTANCE.acquireLock(customer, AppSessionManager.getSession().getId());
    customer.loadVersion(version);
    getReq().getSession().setAttribute("customer", customer);
  }
}
