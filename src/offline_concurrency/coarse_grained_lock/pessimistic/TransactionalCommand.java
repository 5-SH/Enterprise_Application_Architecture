package offline_concurrency.coarse_grained_lock.pessimistic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransactionalCommand implements Command {
  private Command impl;

  public TransactionalCommand(Command impl) {
    this.impl = impl;
  }

  @Override
  public void init(HttpServletRequest req, HttpServletResponse rsp) {
    this.impl.init(req, rsp);
  }

  @Override
  public void process() throws Exception {
    // beginSystemTransaction();
    try {
      this.impl.process();
      // commitSystemTransaction();
    } catch (Exception e) {
      // rollbackSystemTransaction();
      throw e;
    }
  }
}
