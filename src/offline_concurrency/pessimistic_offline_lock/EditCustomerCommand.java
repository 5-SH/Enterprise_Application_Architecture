package offline_concurrency.pessimistic_offline_lock;

import object_relation.metadata_mapping.metadata_mapping.Mapper;

public class EditCustomerCommand extends BusinessTransactionCommand {
  @Override
  public void process() throws Exception {
    startNewBusinessTransaction();
    Long customerId = new Long(getReq().getParameter("customer_id"));
    ExclusiveReadLockManagerDBImpl.INSTANCE.acquireLock(customerId, AppSessionManager.getSession().getId());
  }
}
