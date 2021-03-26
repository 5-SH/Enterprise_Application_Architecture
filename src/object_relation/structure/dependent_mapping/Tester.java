package object_relation.structure.dependent_mapping;

public class Tester {
  public static void main(String[] args) {
    AlbumMapper albumMapper = new AlbumMapper();
    Album album = albumMapper.find(1);
    System.out.println(album.toString());

    // update
    album.removeTrack(0);
    album.addTrack(new Track("B"));
    album.addTrack(new Track("C"));
    album.addTrack(new Track("D"));

    albumMapper.update(album);
    System.out.println(album.toString());
  }
}
