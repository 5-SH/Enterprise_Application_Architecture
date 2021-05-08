package web_presentation.front_controller;

import web_presentation.page_controller.Album;
import web_presentation.page_controller.AlbumHelper;

import javax.servlet.ServletException;
import java.io.IOException;

public class AlbumCommand extends FrontCommand {
  @Override
  public void process() throws ServletException, IOException {
    Album album = Album.findByTitle(request.getParameter("title"));
    if (album == null) {
      forward("/view/MissingAlbumError.jsp");
    } else {
      request.setAttribute("helper", new AlbumHelper(album));
      forward("/view/album.jsp");
    }
  }
}
