package web_presentation.front_controller;

import web_presentation.page_controller.Artist;
import web_presentation.page_controller.ArtistHelper;

import javax.servlet.ServletException;
import java.io.IOException;

public class ArtistCommand extends FrontCommand {
  @Override
  public void process() throws ServletException, IOException {
    Artist artist = Artist.findByName(request.getParameter("name"));
    if (artist == null) {
      forward("/view/MissingArtistError.jsp");
    } else {
      request.setAttribute("helper", new ArtistHelper(artist));
      forward("/view/artist.jsp");
    }
  }
}
