package object_relation.structure.class_table_inheritance;

import java.sql.PreparedStatement;
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

  @Override
  protected Player find(long id) {
    Player result = null;
    try {
      ResultSet rs = findRow(id);
      String type = rs.getString("type");
      switch (type) {
        case BowlerMapper.TYPE_CODE:
          result = (Player) bmapper.find(id);
        case CricketerMapper.TYPE_CODE:
          result = (Player) cmapper.find(id);
        case FootballerMapper.TYPE_CODE:
          result = (Player) fmapper.find(id);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  private ResultSet findRow(long id) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = DB.prepareStatement("SELECT type FROM athlete WHERE id = ?");
      stmt.setLong(1, id);
      rs = stmt.executeQuery();
      rs.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rs;
  }
}
