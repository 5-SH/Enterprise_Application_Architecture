package distribution_patterns.remote_facade;

import distribution_patterns.data_transfer_object.AlbumDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface AlbumService extends Remote {
  Map getAlbum(String id) throws RemoteException;
  void createAlbum(String id, Map album) throws RemoteException;
  void updateAlbum(String id, Map album) throws RemoteException;
}
