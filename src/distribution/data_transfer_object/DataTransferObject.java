package distribution.data_transfer_object;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTransferObject {
  public Map writeMapReflect() {
    Map result = null;
    try {
      Field[] fields = this.getClass().getDeclaredFields();
      result = new HashMap();
      for (int i = 0; i < fields.length; i++) {
        fields[i].setAccessible(true);
        if (fields[i].getName() == "tracks") {
          TrackDTO[] tracks = (TrackDTO[]) fields[i].get(this);
          List mapList = new ArrayList();
          for (int j = 0; j < tracks.length; j++) {
            mapList.add(tracks[j].writeMap());
          }
          result.put(fields[i].getName(), mapList.toArray(new Map[0]));
        } else {
          result.put(fields[i].getName(), fields[i].get(this));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public static AlbumDTO readMapReflect(Map arg) {
    AlbumDTO result = new AlbumDTO();
    try {
      Field[] fields = result.getClass().getDeclaredFields();
      for (int i = 0; i < fields.length; i++) {
        fields[i].setAccessible(true);
        if (fields[i].getName() == "tracks") {
          Map[] tracks = (Map[]) arg.get("tracks");
          List trackList = new ArrayList();
          for (int j = 0; j < tracks.length; j++) {
            trackList.add(TrackDTO.readMap(tracks[j]));
          }
          result.setTracks((TrackDTO[]) trackList.toArray(new TrackDTO[0]));
        } else {
          fields[i].set(result, arg.get(fields[i].getName()));
        }
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    return result;
  }
}
