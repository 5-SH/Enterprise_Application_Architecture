package offline_concurrency.pessimistic_offline_lock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.IdentityHashMap;

public abstract class BusinessTransactionCommand implements Command {
  private HttpServletRequest req;
  private HttpServletResponse rsp;

  protected final String APP_SESSION = "appSession";
  protected final String LOCK_REMOVER = "lockRemover";


  @Override
  public void init(HttpServletRequest req, HttpServletResponse rsp) {
    this.req = req;
    this.rsp = rsp;
  }

  protected void startNewBusinessTransaction() {
    HttpSession httpSession = getReq().getSession(true);
    AppSession appSession = (AppSession) httpSession.getAttribute(APP_SESSION);
    if (appSession != null) {
      ExclusiveReadLockManagerDBImpl.INSTANCE.releaseAllLocks(appSession.getId());
    }
    appSession = new AppSession(getReq().getRemoteUser(), httpSession.getId(), new IdentityHashMap());
    AppSessionManager.setSession(appSession);
    httpSession.setAttribute(APP_SESSION, appSession);
    httpSession.setAttribute(LOCK_REMOVER, new LockRemover(appSession.getId()));
  }

  protected void continueBusinessTransaction() {
    HttpSession httpSession = getReq().getSession();
    AppSession appSession = (AppSession) httpSession.getAttribute(APP_SESSION);
    AppSessionManager.setSession(appSession);
  }

  protected HttpServletRequest getReq() {
    return this.req;
  }

  protected HttpServletResponse getRsp() {
    return this.rsp;
  }
}
