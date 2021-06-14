package distribution_patterns.remote_facade;

import distribution_patterns.data_transfer_object.AlbumAssembler;
import distribution_patterns.data_transfer_object.AlbumDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class AlbumServiceBean extends UnicastRemoteObject implements AlbumService {
  public AlbumServiceBean() throws RemoteException {
  }

  @Override
  public Map getAlbum(String id) throws RemoteException {
    return new AlbumAssembler().writeDTO(Registry.findAlbum(id)).writeMapReflect();
//    return AlbumDTO.readMapReflect(resultMap);
  }
}
