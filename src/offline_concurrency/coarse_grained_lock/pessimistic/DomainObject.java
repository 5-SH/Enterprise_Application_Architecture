package offline_concurrency.coarse_grained_lock.pessimistic;

import java.sql.Timestamp;

public class DomainObject {
  private Long id;
  private Timestamp modified;
  private String modifiedBy;
  private Version version;

  public DomainObject(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public Version getVersion() {
    return version;
  }

  public void setSystemFields(Version version, Timestamp modified, String modifiedBy) {
    this.version = version;
    this.modified = modified;
    this.modifiedBy = modifiedBy;
  }
}
