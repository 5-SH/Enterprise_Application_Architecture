package offline_concurrency.pessimistic_offline_lock;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LockRemover implements HttpSessionBindingListener {
  private String sessionId;

  public LockRemover(String sessionId) {
    this.sessionId = sessionId;
  }

  @Override
  public void valueBound(HttpSessionBindingEvent event) {
    HttpSessionBindingListener.super.valueBound(event);
  }

  @Override
  public void valueUnbound(HttpSessionBindingEvent event) {
    try {
      // beginSystemTransaction();
      ExclusiveReadLockManagerDBImpl.INSTANCE.releaseAllLocks(this.sessionId);
      // commitSystemTransaction();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
