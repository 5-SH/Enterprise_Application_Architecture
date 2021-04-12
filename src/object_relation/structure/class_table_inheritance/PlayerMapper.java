package object_relation.structure.class_table_inheritance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerMapper extends Mapper {
  private BowlerMapper bmapper;
  private CricketerMapper cmapper;
  private FootballerMapper fmapper;

  public PlayerMapper() {
    this.bmapper = new BowlerMapper();
    this.cmapper = new CricketerMapper();
    this.fmapper = new FootballerMapper();
  }

  public Player find(long id) {
    Player result = null;
    try {
      ResultSet rs = findRow(id);
      String type = rs.getString("type");
      switch (type) {
        case BowlerMapper.TYPE_CODE:
          result = (Player) bmapper.find(id);
          break;
        case CricketerMapper.TYPE_CODE:
          result = (Player) cmapper.find(id);
          break;
        case FootballerMapper.TYPE_CODE:
          result = (Player) fmapper.find(id);
          break;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  @Override
  protected void update(DomainObject obj) {
    Player player = (Player) obj;
    String type = player.getType();
    switch (type) {
      case BowlerMapper.TYPE_CODE:
        bmapper.update(obj);
        break;
      case CricketerMapper.TYPE_CODE:
        cmapper.update(obj);
        break;
      case FootballerMapper.TYPE_CODE:
        fmapper.update(obj);
        break;
    }
  }

  @Override
  protected void insert(DomainObject obj) throws SQLException {

  }
}
