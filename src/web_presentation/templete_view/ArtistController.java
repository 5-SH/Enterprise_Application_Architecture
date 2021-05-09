package web_presentation.templete_view;

import web_presentation.page_controller.Artist;
import web_presentation.page_controller.ArtistHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArtistController extends ActionServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Artist artist = Artist.findByName(req.getParameter("name"));
    if (artist == null) {
      forward("/view/MissingArtistError.jsp", req, resp);
    } else {
      req.setAttribute("helper", new ArtistHelper(artist));
      forward("/view/templete_artist.jsp", req, resp);
    }
  }
}
