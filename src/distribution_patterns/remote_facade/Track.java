package distribution_patterns.remote_facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Track implements Serializable {
  private String title;
  private List performers = new ArrayList();

  public Track(String title) {
    this.title = title;
  }

  public Track(String title, List performers) {
    this.title = title;
    this.performers = performers;
  }

  public String getTitle() {
    return title;
  }

  public List getPerformers() {
    return performers;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPerformers(List performers) {
    this.performers = performers;
  }

  public void clearPerformers() {
    this.performers = new ArrayList();
  }

  public void addPerformers(Artist artist) {
    this.performers.add(artist);
  }

  @Override
  public String toString() {
    return "Track{" +
      "title='" + title + '\'' +
      ", performers=" + performers +
      '}';
  }
}
