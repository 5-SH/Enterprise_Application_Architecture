package distribution_patterns.remote_facade;

import distribution_patterns.data_transfer_object.AlbumDTO;

import java.util.List;
import java.util.Map;

public class RemoteClient {
  public static void main(String[] args) {
    RemoteClient client = new RemoteClient();
    client.process(args[0]);
  }

  public void process(String id) {
    try {
      AlbumHome albumHome = new AlbumHome();
      AlbumService albumService = albumHome.create();
      Map albumMap = albumService.getAlbum(id);
      AlbumDTO result = AlbumDTO.readMapReflect(albumMap);

      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
