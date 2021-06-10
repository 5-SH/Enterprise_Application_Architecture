package distribution_patterns.remote_facade;

public class AlbumAssembler {
  public AlbumDTO writeDTO(Album album) {
    return new AlbumDTO(album);
  }
}
