package web_presentation.front_controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionServlet extends HttpServlet {
  protected void forward(String target, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(target);
    dispatcher.forward(req, resp);
  }
}
