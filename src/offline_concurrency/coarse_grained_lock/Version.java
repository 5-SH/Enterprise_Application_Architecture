package offline_concurrency.coarse_grained_lock;

import basic.plugin.IdGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class Version {
  private Long id;
  private long value;
  private String modifiedBy;
  private Timestamp modified;
  private boolean locked;
  private boolean isNew;

  public void increaseValue() {
    this.value++;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public boolean isNew() { return isNew; }

  public void setNew(boolean aNew) {
    isNew = aNew;
  }

  public Long getId() {
    return id;
  }

  public long getValue() {
    return value;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public Timestamp getModified() {
    return modified;
  }

  public boolean isLocked() {
    return locked;
  }

  public Version(Long id, long value, String modifiedBy, Timestamp modified) {
    this.id = id;
    this.value = value;
    this.modifiedBy = modifiedBy;
    this.modified = modified;
  }

  public static Version find(Long id) {
    Version version = AppSessionManager.getSession().getVersion(id);
    if (version == null) version = MapperRegistry.getMapper("BaseMapper").loadVersion(id);
    return version;
  }

  public static Version create() {
    Version version = new Version(IdGenerator.INSTANCE.nextId(), 0, AppSessionManager.getSession().getUser(), new Timestamp(System.currentTimeMillis()));
    version.isNew = true;
    return version;
  }
}
