package offline_concurrency.coarse_grained_lock.pessimistic;

public interface ExclusiveReadLockManager {
  Version acquireLock(DomainObject object, String owner) throws Exception;
  void releaseLock(DomainObject object, String owner) throws Exception;
//  void releaseAllLocks(String owner, String owner);
}
