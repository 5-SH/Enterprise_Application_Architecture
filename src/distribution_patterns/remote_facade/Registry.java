package distribution_patterns.remote_facade;

import java.util.HashMap;
import java.util.Map;

public class Registry {
  private static Registry soleInstance = new Registry();
  protected Map albums;

  public Registry() {
    albums = new HashMap();
    Album album1 = new Album("osh album #1", new Artist("osh1"));
    album1.addTrack(new Track("track #1-1"));
    album1.addTrack(new Track("track #1-2"));
    album1.addTrack(new Track("track #1-3"));
    albums.put("1", album1);

    Album album2 = new Album("osh album #2", new Artist("osh2"));
    album2.addTrack(new Track("track #2-1"));
    album2.addTrack(new Track("track #2-2"));
    album2.addTrack(new Track("track #2-3"));
    albums.put("2", album2);
  }

  private static Registry getInstance() { return soleInstance; }

  public static void initialize() { soleInstance = new Registry(); }

  public static Album findAlbum(String id) { return (Album) getInstance().albums.get(id); }
}
