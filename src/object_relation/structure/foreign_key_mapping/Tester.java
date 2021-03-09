package object_relation.structure.foreign_key_mapping;

public class Tester {
  public static void main(String[] args) {
    Artist artist = MapperRegistry.artist().find(1);
    System.out.println(artist.toString());

    Album album = MapperRegistry.album().find(1);
    System.out.println(album.toString());

    // update
    artist.setName("주애린");
    MapperRegistry.artist().update(artist);
    System.out.println(album.toString());

    album.setTitle("무제");
    MapperRegistry.album().update(album);
    System.out.println(album.toString());

  }
}
