package offline_concurrency.implicit_lock;

public class LockingMapper implements Mapper {
  private Mapper impl;

  public LockingMapper(Mapper impl) {
    this.impl = impl;
  }

  @Override
  public DomainObject find(Long id) {
    ExclusiveReadLockManagerDBImpl.INSTANCE.acquireLock(id, AppSessionManager.getSession().getId());
    return impl.find(id);
  }

  @Override
  public void insert(DomainObject obj) {
    impl.insert(obj);
  }

  @Override
  public void update(DomainObject obj) {
    impl.update(obj);
  }

  @Override
  public void delete(DomainObject obj) {
    impl.delete(obj);
  }


}
