package offline_concurrency.pessimistic_offline_lock;

import object_relation.metadata_mapping.metadata_mapping.Mapper;

public class EditCustomerCommand extends BusinessTransactionCommand {
  @Override
  public void process() throws Exception {
    startNewBusinessTransaction();
    Long customerId = new Long(getReq().getParameter("customer_id"));
    ExclusiveReadLockManagerDBImpl.INSTANCE.acquireLock(customerId, AppSessionManager.getSession().getId());
    CustomerMapper customerMapper = (CustomerMapper) MapperRegistry.getMapper("CustomerMapper");
    Customer customer = (Customer) customerMapper.find(customerId);
    getReq().getSession().setAttribute("customer", customer);

    System.out.println("customer : " + customer.toString());
//    forward("/editCustomer.jsp");
  }
}
