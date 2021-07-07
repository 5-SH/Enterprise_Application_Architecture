package offline_concurrency.coarse_grained_lock.root_optimistic;

import basic.plugin.IdGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;

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

//  public static Version find(Long id) {
//    Version version = AppSessionManager.getSession().getVersion(id);
//    if (version == null) version = MapperRegistry.getMapper("BaseMapper").loadVersion(id);
//    return version;
//  }

  public static Version create() {
    Version version = new Version(IdGenerator.INSTANCE.nextId(), 0, "admin", new Timestamp(System.currentTimeMillis()));
    version.isNew = true;
    return version;
  }

  public void insert() {
    Connection conn = ConnectionManager.getConnection();
    if (isNew()) {
      try {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO version VALUES (?, ?, ?, ?)");
        stmt.setLong(1, getId());
        stmt.setLong(2, getValue());
        stmt.setString(3, getModifiedBy());
        stmt.setTimestamp(4, getModified());
        stmt.executeUpdate();

        setNew(false);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void increase() {
    Connection conn = ConnectionManager.getConnection();
    try {
      PreparedStatement stmt = conn.prepareStatement("UPDATE version SET value =?, modifiedBy =?, modified = ? WHERE id = ? and value = ?");
      stmt.setLong(1, getValue() + 1);
      stmt.setString(2, "admin");
      stmt.setTimestamp(3,  new Timestamp(System.currentTimeMillis()));
      stmt.setLong(4, getId());
      stmt.setLong(5, getValue());

      int rowCount = stmt.executeUpdate();
      if (rowCount == 0) {
        throw new Exception("version modified by " + getModifiedBy() + " at " +
          DateFormat.getDateTimeInstance().format(getModified()));
      }

      increaseValue();
      setLocked(true);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public String toString() {
    return "Version{" +
      "id=" + id +
      ", value=" + value +
      ", modifiedBy='" + modifiedBy + '\'' +
      ", modified=" + modified +
      ", locked=" + locked +
      ", isNew=" + isNew +
      '}';
  }
}
