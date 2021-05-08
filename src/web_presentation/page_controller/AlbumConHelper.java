package web_presentation.page_controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlbumConHelper extends HelperController {
  public void init(HttpServletRequest req, HttpServletResponse resp) {
    super.init(req, resp);
    Album album = getAlbum();
    if (album == null) {
      forward("/view/MissingAlbumError.jsp", req, resp);
    }
    else {
      req.setAttribute("helper", new AlbumHelper(album));
      forward("/view/album.jsp", req, resp);
    }
  }

  private Album getAlbum() {
    return Album.findByTitle(super.request.getParameter("title"));
  }
}
