package object_relation.structure.dependent_mapping;

import java.sql.PreparedStatement;
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

  public void update(DomainObject arg) {
    PreparedStatement updateStatement = null;
    try {
      updateStatement = DB.prepareStatement("UPDATE album SET title = ? WHERE id = ?");
      updateStatement.setLong(2, arg.getId().longValue());
      Album album = (Album) arg;
      updateStatement.setString(1, album.getTitle());
      updateStatement.execute();
      updateTracks(album);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void updateTracks(Album arg) throws SQLException {
    PreparedStatement deleteTrackStatement = null;
    try {
      deleteTrackStatement = DB.prepareStatement("DELETE from track WHERE albumID = ?");
      deleteTrackStatement.setLong(1, arg.getId().longValue());
      deleteTrackStatement.execute();
      for (int i = 0; i < arg.getTracks().length; i++) {
        Track track = arg.getTracks()[i];
        insertTrack(track, arg);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insertTrack(Track track, Album album) throws SQLException {
    PreparedStatement insertTracksStatement = null;
    try {
      insertTracksStatement = DB.prepareStatement("INSERT INTO track (albumID, title) VALUES (?, ?)");
      insertTracksStatement.setLong(1, album.getId().longValue());
      insertTracksStatement.setString(2, track.getTitle());
      insertTracksStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
