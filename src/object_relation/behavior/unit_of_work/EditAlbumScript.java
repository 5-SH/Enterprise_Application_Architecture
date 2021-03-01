package object_relation.behavior.unit_of_work;

import java.util.List;

public class EditAlbumScript {
  public static void createAlbumByTitle(List<String> titles) {
    UnitOfWork.newCurrent();
    for (String title : titles) {
      AlbumFactory.create(title);
    }
    UnitOfWork.getCurrent().commit();
  }

  public static void updateTitle(Long albumId, String title) {
    UnitOfWork.newCurrent();
    AlbumMapper mapper = (AlbumMapper) MapperRegistry.getMapper(Album.class.getName());
    Album album = mapper.find(albumId);
    album.setTitle(title);
    UnitOfWork.getCurrent().commit();
  }

  public static void deleteTitle(Long albumId) {
    UnitOfWork.newCurrent();
    AlbumMapper mapper = (AlbumMapper) MapperRegistry.getMapper(Album.class.getName());
    Album album = mapper.find(albumId);
    album.markRemoved();
    UnitOfWork.getCurrent().commit();
  }
}
