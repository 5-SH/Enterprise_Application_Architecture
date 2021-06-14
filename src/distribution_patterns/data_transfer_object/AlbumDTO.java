package distribution_patterns.data_transfer_object;

import distribution_patterns.remote_facade.Artist;

import java.util.Arrays;

public class AlbumDTO extends DataTransferObject {
  private String title;
  private String artist;
  private TrackDTO[] tracks;

  public void setTitle(String title) {
    this.title = title;
  }

  public void setArtist(Artist artist) {
    this.artist = artist.getName();
  }

  public void setTracks(TrackDTO[] tracks) {
    this.tracks = tracks;
  }

  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

  public TrackDTO[] getTracks() {
    return tracks;
  }

  @Override
  public String toString() {
    return "AlbumDTO{" +
      "title='" + title + '\'' +
      ", artist='" + artist + '\'' +
      ", tracks=" + Arrays.toString(tracks) +
      '}';
  }
}
