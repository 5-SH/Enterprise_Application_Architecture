package object_relation.structure.dependent_mapping;

import java.util.ArrayList;
import java.util.List;

public class Album extends DomainObject {
  private List tracks = new ArrayList();
  private String title;

  public Album(Long id, String title) {
    super(id);
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void addTrack(Track arg) {
    tracks.add(arg);
  }

  public void removeTrack(Track arg) {
    tracks.remove(arg);
  }

  public void removeTrack(int i) {
    tracks.remove(i);
  }

  public Track[] getTracks() {
    return (Track[]) tracks.toArray(new Track[tracks.size()]);
  }

  @Override
  public String toString() {
    return "Album{" +
      "tracks=" + tracks +
      ", title='" + title + '\'' +
      '}';
  }
}
