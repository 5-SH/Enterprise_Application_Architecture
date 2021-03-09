package object_relation.structure.foreign_key_mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumMapper extends AbstractMapper {
  public Album find(long id) {
    return (Album) abstractFind(id);
  }

//  @Override
//  protected String findStatement() {
//    return "SELECT ID, title, artistID " +
//      "FROM album " +
//      "WHERE ID = ?";
//  }
//
//  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
//    String title = rs.getString(2);
//    long artistID = rs.getLong(3);
//
//    Artist artist = MapperRegistry.artist().find(artistID);
//    Album result = new Album(id, title, artist);
//
//    return result;
//  }

  // 다중 테이블 검색


  @Override
  protected String findStatement() {
    return "SELECT a.ID, a.title, a.artistID, r.name " +
            "FROM album a, artists r " +
            "WHERE a.ID = ? and a.artistID = r.ID";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    String title = rs.getString(2);
    long artistID = rs.getLong(3);
    ArtistMapper artistMapper = MapperRegistry.artist();
    Artist artist;
    if (artistMapper.isLoaded(artistID)) {
      artist = artistMapper.find(artistID);
    } else {
      artist = loadArtist(artistID, rs);
    }
    Album result = new Album(id, title, artist);
    return result;
  }

  private Artist loadArtist(long id, ResultSet rs) throws SQLException {
    String name = rs.getString(4);
    Artist result = new Artist(new Long(id), name);
    MapperRegistry.artist().doRegister(result.getId(), result);
    return result;
  }

  @Override
  public void update(DomainObject arg) {
    PreparedStatement stmt = null;
    try {
      stmt = DB.prepareStatement("UPDATE album SET title = ?, artistID = ? WHERE id = ?");
      stmt.setLong(3, arg.getId().longValue());
      Album album = (Album) arg;
      stmt.setString(1, album.getTitle());
      stmt.setLong(2, album.getArtist().getId().longValue());
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
