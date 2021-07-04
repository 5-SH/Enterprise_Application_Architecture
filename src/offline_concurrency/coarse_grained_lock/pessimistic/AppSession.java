package offline_concurrency.coarse_grained_lock.pessimistic;

import java.util.IdentityHashMap;

public class AppSession {
  private String user;
  private String id;
  private IdentityHashMap imap;

  public AppSession(String user, String id, IdentityHashMap imap) {
    this.user = user;
    this.id = id;
    this.imap = imap;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public IdentityHashMap getImap() {
    return imap;
  }

  public void setImap(IdentityHashMap imap) {
    this.imap = imap;
  }
}
