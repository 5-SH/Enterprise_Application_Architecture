package distribution.data_transfer_object;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TrackDTO extends DataTransferObject {
  private String title;
  private String[] performers;

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPerformers(String[] performers) {
    this.performers = performers;
  }

  public String getTitle() {
    return title;
  }

  public String[] getPerformers() {
    return performers;
  }

  public Map writeMap() {
    Map result = new HashMap();
    result.put("title", this.title);
    result.put("performers", this.performers);
    return result;
  }

  public static TrackDTO readMap(Map arg) {
    TrackDTO result = new TrackDTO();
    result.title = (String) arg.get("title");
    result.performers = (String[]) arg.get("performers");
    return result;
  }

  @Override
  public String toString() {
    return "TrackDTO{" +
      "title='" + title + '\'' +
      ", performers=" + Arrays.toString(performers) +
      '}';
  }
}
