package object_relation.lazy_load.ghost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListLoader {
  private String sql;
  private List sqlParams;
  private Mapper mapper;

  public List load() {
    List result = new ArrayList();
    try {
      Connection db = mapper.getDb();
      PreparedStatement stmt = db.prepareStatement(sql);
      for (int i = 0; i < sqlParams.size(); i++) {
        stmt.setLong(i + 1, (Long) sqlParams.get(i));
      }
      ResultSet rs = stmt.executeQuery();
      while(rs.next()) {
        TimeRecord timeRecord = new TimeRecord(rs.getLong(1));
        mapper.doLoadLine(rs, timeRecord);
        result.add(timeRecord);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }

  public void setSqlParams(List sqlParams) {
    this.sqlParams = sqlParams;
  }

  public void setMapper(Mapper mapper) {
    this.mapper = mapper;
  }
}
