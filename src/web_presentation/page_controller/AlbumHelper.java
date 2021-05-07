package web_presentation.page_controller;

public class AlbumHelper {
  private Album album;

  public AlbumHelper(Album album) {
    this.album = album;
  }

  public String print() {
    StringBuilder result = new StringBuilder();
    result.append("----------------------<br/>");
    result.append("&nbsp&nbsp ID : " + album.getId() + "<br/>");
    result.append("&nbsp&nbsp 타이틀 : " + album.getTitle() + "<br/>");
    result.append("&nbsp&nbsp 아티스트 ID : " + album.getArtistID() + "<br/>");
    result.append("----------------------<br/>");

    return result.toString();
  }
}
