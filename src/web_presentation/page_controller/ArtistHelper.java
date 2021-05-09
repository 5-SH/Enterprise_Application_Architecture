package web_presentation.page_controller;

import java.util.List;

public class ArtistHelper {
  private Artist artist;

  public ArtistHelper(Artist artist) {
    this.artist = artist;
  }

  public String print() {
    StringBuilder result = new StringBuilder();
    result.append("-------------------<br/>");
    result.append("&nbsp&nbsp ID : " + artist.getId() + "<br/>");
    result.append("&nbsp&nbsp 이름 : " + artist.getName() + "<br/>");
    result.append("-------------------<br/>");

    return result.toString();
  }

  public String getAlbumList() {
    List<Album> albums = Album.findByArtistID(artist.getId());
    StringBuffer result = new StringBuffer();
    result.append("<ul>");
    for (Album album : albums) {
      result.append("<li>");
      result.append(album.getTitle());
      result.append("</li>");
    }
    result.append("</ul>");
    return result.toString();
  }
}
