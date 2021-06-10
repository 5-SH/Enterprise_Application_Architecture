package distribution_patterns.remote_facade;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AlbumServiceBean extends UnicastRemoteObject implements AlbumService {
  public AlbumServiceBean() throws RemoteException {
  }

  @Override
  public AlbumDTO getAlbum(String id) throws RemoteException {
    System.out.println("RemoteServer AlbumServiceBean : " + id + ", " + Registry.findAlbum(id));
    return new AlbumAssembler().writeDTO(Registry.findAlbum(id));
  }
}
