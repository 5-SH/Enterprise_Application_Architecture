package object_relation.behavior.unit_of_work;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumMapper extends Mapper {
  public Album find(Long key) {
    return (Album) AbstractFind(key);
  }

  @Override
  protected String findStatement() {
    return "SELECT * FROM album WHERE id = ?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    String title = rs.getString(2);
    return new Album(id, title);
  }

  @Override
  protected String insertStatement() throws SQLException {
    return "INSERT INTO album VALUES (?, ?)";
  }

  @Override
  protected void doInsert(DomainObject obj, PreparedStatement stmt) throws SQLException {
    Album album = (Album) obj;
    stmt.setString(2, album.getTitle());
  }

  @Override
  protected String updateStatement() throws SQLException {
    return "UPDATE album SET title = ? WHERE id = ?";
  }

  @Override
  protected void doUpdate(DomainObject obj, PreparedStatement stmt) throws SQLException {
    Album album = (Album) obj;
    stmt.setString(1, album.getTitle());
  }

  @Override
  protected String deleteStatement() throws SQLException {
    return "DELETE FROM album WHERE id = ?";
  }
}
