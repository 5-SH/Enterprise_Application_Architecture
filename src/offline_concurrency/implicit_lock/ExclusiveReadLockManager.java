package offline_concurrency.implicit_lock;

public interface ExclusiveReadLockManager {
  void acquireLock(Long lockable, String owner) throws Exception;
  void releaseLock(Long lockable, String owner) throws Exception;
  void releaseAllLocks(String owner);
}
