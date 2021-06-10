package distribution_patterns.remote_facade;

import java.io.Serializable;

public class Track implements Serializable {
  private String title;

  public Track(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return "Track{" +
      "title='" + title + '\'' +
      '}';
  }
}
