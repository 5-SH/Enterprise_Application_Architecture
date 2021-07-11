package offline_concurrency.implicit_lock;

public interface ExclusiveReadLockManager {
  void acquireLock(Long lockable, String owner);
  void releaseLock(Long lockable, String owner);
  void releaseAllLocks(String owner);
}
