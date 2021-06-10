package distribution_patterns.remote_facade;

public class RemoteClient {
  public static void main(String[] args) {
    RemoteClient client = new RemoteClient();
    client.process(args[0]);
  }

  public void process(String id) {
    try {
      AlbumHome albumHome = new AlbumHome();
      AlbumService albumService = albumHome.create();
      AlbumDTO albumDTO = albumService.getAlbum(id);
      System.out.println(albumDTO.read());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
