package object_relation.structure.dependent_mapping;

public class Tester {
  public static void main(String[] args) {
    AlbumMapper albumMapper = new AlbumMapper();
    Album album = albumMapper.find(1);
    System.out.println(album.toString());
  }
}
