package object_relation.structure.foreign_key_mapping.single_valued_reference;

import java.sql.PreparedStatement;
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

  @Override
  public void update(DomainObject arg) {
    PreparedStatement stmt = null;
    try {
      stmt = DB.prepareStatement("UPDATE artists SET name = ? WHERE id = ?");
      stmt.setLong(2, arg.getId().longValue());
      Artist artist = (Artist) arg;
      stmt.setString(1, artist.getName());
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public boolean isLoaded(long id) {
    return loadedMap.containsKey(id);
  }
}
