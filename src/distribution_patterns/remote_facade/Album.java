package distribution_patterns.remote_facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {
  private String title;
  private Artist artist;
  private List tracks = new ArrayList();

  public Album(String title, Artist artist) {
    this.title = title;
    this.artist = artist;
  }

  public String getTitle() {
    return title;
  }

  public Artist getArtist() {
    return artist;
  }

  public List getTracks() {
    return tracks;
  }

  public void removeTrack(int i) {
    tracks.remove(i);
  }

  public void addTrack(Track arg) {
    tracks.add(arg);
  }

  @Override
  public String toString() {
    return "Album{" +
      "title='" + title + '\'' +
      ", artist=" + artist +
      ", tracks=" + tracks +
      '}';
  }
}
