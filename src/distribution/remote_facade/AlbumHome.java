package distribution.remote_facade;

import java.rmi.Naming;

// 네이밍 서비스에서 원격 파사드에 대한 접근을 얻는데 사용
public class AlbumHome {
  public AlbumService create() {
    AlbumService service = null;
    try {
      service = (AlbumService) Naming.lookup("rmi://127.0.0.1/AlbumService");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return service;
  }
}
