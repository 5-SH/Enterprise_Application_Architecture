package object_relation.structure.foreign_key_mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumMapper extends AbstractMapper {
  public Album find(long id) {
    return (Album) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT a.ID, a.title, a.artistID, r.name " +
      "FROM album a, artists r " +
      "WHERE a.ID = ? and a.artistID = r.ID";
  }

  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    String title = rs.getString(2);
    long artistID = rs.getLong(3);

    Artist artist = MapperRegistry.artist().find(artistID);
    Album result = new Album(id, title, artist);

    return result;
  }
}
