package object_relation.lazy_load.ghost;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TimeRecordMapper extends Mapper {
  public static String FIND_FOR_EMPLOYEE_SQL
    = "SELECT * FROM timerecord WHERE employee_id = ?";

  public TimeRecord find(Long key) {
    return (TimeRecord) AbstractFind(key);
  }

  @Override
  public DomainObject createGhost(Long key) {
    return new TimeRecord(key);
  }

  @Override
  protected String findStatement() {
    return "SELECT * FROM timerecord WHERE id = ?";
  }

  @Override
  protected void doLoadLine(ResultSet rs, DomainObject obj) {
    try {
      TimeRecord timeRecord = (TimeRecord) obj;
      timeRecord.setEmployee_id(rs.getLong(2));
      timeRecord.setStartTime(rs.getString(3));
      timeRecord.setEndTime(rs.getString(4));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
