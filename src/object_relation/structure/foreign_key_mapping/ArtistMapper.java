package object_relation.structure.foreign_key_mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistMapper extends AbstractMapper {
  public Artist find(long id) {
    return (Artist) abstractFind(id);
  }

  @Override
  protected String findStatement() {
    return "SELECT ID, name FROM artists WHERE ID = ?";
  }

  @Override
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    String name = rs.getString(2);
    Artist artist = new Artist(id, name);
    return artist;
  }
}
