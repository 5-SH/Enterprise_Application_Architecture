package object_relation.structure.concrete_table_inheritance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CricketerMapper extends AbstractPlayerMapper {
  public static final String TYPE_CODE = "C";
  private static final String TABLENAME = "concrete_cricketer";

  @Override
  protected DomainObject find(long id) throws SQLException {
    return abstractFind(id, TABLENAME);
  }

  @Override
  protected DomainObject createDomainObject() {
    return new Cricketer();
  }

  @Override
  protected void load(DomainObject obj, ResultSet rs) throws SQLException {
    super.load(obj, rs);
    Cricketer cricketer = (Cricketer) obj;
    cricketer.setBattingAverage(rs.getString("batting_average"));
  }

  @Override
  protected String updateStatement() {
    return "UPDATE concrete_cricketer SET name = ?, type = ?, batting_average = ? WHERE id = ?";
  }

  protected void save(DomainObject obj, PreparedStatement stmt) throws SQLException {
    super.save(obj, stmt);
    Cricketer cricketer = (Cricketer) obj;
    stmt.setString(3, cricketer.getBattingAverage());
  }

  protected void insert(DomainObject obj) {
    super.insert(obj);
  }

  @Override
  protected String insertStatement() {
    return "INSERT INTO concrete_cricketer (`name`, `type`, `batting_average`, `id`) VALUES (?, ?, ?, ?)";
  }
}
