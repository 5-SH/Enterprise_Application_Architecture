package distribution.remote_facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface AlbumService extends Remote {
  Map getAlbum(String id) throws RemoteException;
  void createAlbum(String id, Map album) throws RemoteException;
  void updateAlbum(String id, Map album) throws RemoteException;
}
