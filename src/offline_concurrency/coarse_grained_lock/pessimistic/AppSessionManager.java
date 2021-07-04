package offline_concurrency.coarse_grained_lock.pessimistic;

public class AppSessionManager {
  private static ThreadLocal current = new ThreadLocal();

  public static AppSession getSession() {
    return (AppSession) current.get();
  }

  public static void setSession(AppSession session) {
    current.set(session);
  }
}
