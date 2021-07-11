package offline_concurrency.implicit_lock;

public interface Mapper {
  DomainObject find(Long id);
  void insert(DomainObject obj);
  void update(DomainObject obj);
  void delete(DomainObject obj);
}
