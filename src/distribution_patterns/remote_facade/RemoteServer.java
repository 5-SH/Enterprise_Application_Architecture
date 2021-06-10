package distribution_patterns.remote_facade;

import java.rmi.Naming;

public class RemoteServer {

  public static void main(String[] args) {
    try {
      AlbumService albumService = new AlbumServiceBean();
      Naming.rebind("AlbumService", albumService);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
