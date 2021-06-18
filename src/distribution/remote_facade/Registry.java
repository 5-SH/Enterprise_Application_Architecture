package distribution.remote_facade;

import java.util.HashMap;
import java.util.Map;

public class Registry {
  private static Registry soleInstance = new Registry();
  protected Map albums;
  protected Map artists;

  public Registry() {
    artists = new HashMap();
    artists.put("kks", new Artist("kks"));
    artists.put("lyj", new Artist("lyj"));
    artists.put("kms", new Artist("kms"));
    artists.put("jjh", new Artist("jjh"));

    albums = new HashMap();
  }

  private static Registry getInstance() { return soleInstance; }

  public static void initialize() { soleInstance = new Registry(); }

  public static Album findAlbum(String id) {
    return (Album) getInstance().albums.get(id);
  }

  public static Artist findArtistNamed(String name) {
    return (Artist) getInstance().artists.get(name);
  }

  public static void addAlbum(String id, Album album) {
    getInstance().albums.put(id, album);
  }
}
