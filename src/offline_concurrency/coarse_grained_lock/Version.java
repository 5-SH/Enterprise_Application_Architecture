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
  private static final String UPDATE_SQL = "UPDATE version SET VALUE =?, modifiedBy =?, modified = ? " + "WHERE id = ? and value = ?";
  private static final String DELETE_SQL = "DELETE FROM version WHERE id = ? and value =?";
  private static final String INSERT_SQL = "INSERT INTO version VALUES (?, ?, ?, ? ,?)";
  private static final String LOAD_SQL = " SELECT id, value, modifiedBy, modified FROM version WHERE id = ?";

  public Version(Long id, long value, String modifiedBy, Timestamp modified) {
    this.id = id;
    this.value = value;
    this.modifiedBy = modifiedBy;
    this.modified = modified;
  }

  public static Version find(Long id) {
    Version version = AppSessionManager.getSession().getVersion(id);
    if (version == null) version = MapperRegistry.getMapper("CustomerMapper").load(id);
    return version;
  }

  public static Version create() {
    Version version = new Version(IdGenerator.INSTANCE.nextId(), 0, AppSessionManager.getSession().getUser(), new Timestamp(System.currentTimeMillis()));
    version.isNew = true;
    return version;
  }
}
