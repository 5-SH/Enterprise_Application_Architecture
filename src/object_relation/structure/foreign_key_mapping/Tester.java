package object_relation.structure.foreign_key_mapping;

public class Tester {
  public static void main(String[] args) {
    Artist artist = MapperRegistry.artist().find(1);
    System.out.println(artist.toString());

    Album album = MapperRegistry.album().find(1);
    System.out.println(album.toString());
  }
}
