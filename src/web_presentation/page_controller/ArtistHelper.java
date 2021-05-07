package web_presentation.page_controller;

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
}
