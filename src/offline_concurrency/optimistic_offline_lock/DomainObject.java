package offline_concurrency.optimistic_offline_lock;

import java.sql.Timestamp;

public class DomainObject {
  private Long id;
  private Timestamp modified;
  private String modifiedBy;
  private int version;

  public DomainObject() {}

  public DomainObject(Long id) { this.id = id; }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Timestamp getModified() {
    return modified;
  }

  public void setModified(Timestamp modified) {
    this.modified = modified;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}
