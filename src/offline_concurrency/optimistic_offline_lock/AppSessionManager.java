package offline_concurrency.optimistic_offline_lock;

import java.util.HashMap;
import java.util.Map;

public class AppSessionManager {
  private static AppSessionManager soleInstance = new AppSessionManager();
  protected Map<Long, DomainObject> identityMap;

  public AppSessionManager() {
    identityMap = new HashMap();
  }

  private static AppSessionManager getInstance() { return soleInstance; }

  public static void initialize() { soleInstance = new AppSessionManager(); }

  public static AppSessionManager getSession() { return soleInstance; }

  public Map getIdentityMap() {
    return identityMap;
  }
}
