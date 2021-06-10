package distribution_patterns.remote_facade;

import java.io.Serializable;

public class AlbumDTO implements Serializable {
  private Album album;

  public AlbumDTO(Album album) {
    this.album = album;
  }

  public String read() {
    return album.toString();
  }

  public Album getAlbum() {
    return album;
  }
}
