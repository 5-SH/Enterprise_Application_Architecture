package web_presentation.page_controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelperController extends HttpServlet {
  protected HttpServletRequest request;
  protected HttpServletResponse response;

  public void init(HttpServletRequest req, HttpServletResponse resp) {
    this.request = req;
    this.response = resp;
  }

  protected void forward(String target, HttpServletRequest req, HttpServletResponse resp) {
    try {
      RequestDispatcher dispatcher = request.getRequestDispatcher(target);
      if (dispatcher == null) resp.sendError(404);
      else dispatcher.forward(req, resp);
    } catch (IOException | ServletException e) {
      e.printStackTrace();
    }
  }
}
