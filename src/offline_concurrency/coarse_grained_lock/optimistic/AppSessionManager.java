package offline_concurrency.coarse_grained_lock.optimistic;

import java.util.HashMap;
import java.util.Map;

public class AppSessionManager {
  private static AppSessionManager soleInstance = new AppSessionManager();
  protected Map<Long, Version> identityMap;

  public AppSessionManager() { identityMap = new HashMap<Long, Version>(); }

  public static void initialize() { soleInstance = new AppSessionManager(); }

  public static AppSessionManager getSession() { return soleInstance; }

  public Map getIdentityMap() { return identityMap; }

  public Version getVersion(Long id) {
    return (Version) getIdentityMap().get(id);
  }

  public void putVersion(Long id, Version version) {
    getIdentityMap().put(id, version);
  }

  public void deleteVersion(Long id) {
    getIdentityMap().remove(id);
  }

  public String getUser() { return "admin"; }
}
