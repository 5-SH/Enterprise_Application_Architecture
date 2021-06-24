package offline_concurrency.pessimistic_offline_lock;

public class SaveCustomerCommand extends BusinessTransactionCommand {
  @Override
  public void process() throws Exception {
    continueBusinessTransaction();

  }
}
