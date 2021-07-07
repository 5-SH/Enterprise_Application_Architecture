package offline_concurrency.coarse_grained_lock.root_optimistic;

import java.sql.Timestamp;

public class DomainObject {
  private Long id;
  private DomainObject parent;
  private Timestamp modified;
  private String modifiedBy;
  private Version version;

  public DomainObject() {}

  public DomainObject(Long id) {
    this.id = id;
  }

  public DomainObject(Long id, DomainObject parent) {
    this.id = id;
    this.parent = parent;
  }

  public Version getVersion() {
    return version;
  }

  public void setSystemFields(Version version, Timestamp modified, String modifiedBy) {
    this.version = version;
    this.modified = modified;
    this.modifiedBy = modifiedBy;
  }

  public DomainObject getParent() {
    return parent;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  protected void markNew() {
    UnitOfWork.getCurrent().registerNew(this);
  }

  protected void markClean() {
    UnitOfWork.getCurrent().registerClean(this);
  }

  protected void markDirty() {
    UnitOfWork.getCurrent().registerDirty(this);
  }

  protected void markRemoved() {
    UnitOfWork.getCurrent().registerRemoved(this);
  }
}
