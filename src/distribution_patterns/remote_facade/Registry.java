package distribution_patterns.remote_facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    Album album1 = new Album("osh album #1", new Artist("osh1"));
    Track t_1_1 = new Track("track #1-1");

    t_1_1.addPerformers(new Artist("kks"));
    album1.addTrack(t_1_1);

    Track t_1_2 = new Track("track #1-2");
    t_1_2.addPerformers(new Artist("lyj"));
    album1.addTrack(t_1_2);

    Track t_1_3 = new Track("track #1-3");
    t_1_3.addPerformers(new Artist("kms"));
    album1.addTrack(t_1_3);

    albums.put("1", album1);

    Album album2 = new Album("osh album #2", new Artist("osh2"));
    album2.addTrack(new Track("track #2-1"));
    album2.addTrack(new Track("track #2-2"));
    album2.addTrack(new Track("track #2-3"));
    albums.put("2", album2);
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
