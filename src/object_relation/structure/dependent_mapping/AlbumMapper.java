package object_relation.structure.dependent_mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumMapper extends AbstractMapper {
  public Album find(long id) {
    return (Album) abstractFind(id);
  }

  protected String findStatement() {
    return "SELECT a.ID, a.title, t.title as trackTitle " +
          "FROM album a, track t " +
          "WHERE a.ID = ? AND t.albumID = a.ID " +
          "ORDER BY t.seq";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    String title = rs.getString(2);
    Album result = new Album(id, title);
    loadTracks(result, rs);

    return result;
  }

  public void loadTracks(Album arg, ResultSet rs) throws SQLException {
    arg.addTrack(newTrack(rs));
    while (rs.next()) {
      arg.addTrack(newTrack(rs));
    }
  }

  private Track newTrack(ResultSet rs) throws SQLException {
    String title = rs.getString(3);
    Track newTrack = new Track(title);
    return newTrack;
  }
}
