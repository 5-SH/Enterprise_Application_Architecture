package object_relation.structure.class_table_inheritance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerMapper extends Mapper {
  private FootballerMapper fmapper;

  public PlayerMapper() {
    this.fmapper = new FootballerMapper();
  }

  @Override
  protected Player find(long id) {
    Player result = null;
    try {
      ResultSet rs = findRow(id, AbstractPlayerMapper.TABLENAME);
      if (rs == null) return null;

      String type = rs.getString("type");
      switch (type) {
        case FootballerMapper.TYPE_CODE:
          result = (Player) fmapper.find(id);
          break;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }


}
