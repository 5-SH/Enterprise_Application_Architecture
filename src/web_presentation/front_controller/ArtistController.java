package web_presentation.front_controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArtistController extends ActionServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Artist artist = Artist.findByName(req.getParameter("name"));
    if (artist == null) {
      forward("/view/front_controller/MissingArtistError.jsp", req, resp);
    } else {
      System.out.println(artist);
    }
  }
}
