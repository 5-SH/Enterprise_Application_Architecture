package distribution_patterns.remote_facade;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AlbumService extends Remote {
  AlbumDTO getAlbum(String id) throws RemoteException;
}
