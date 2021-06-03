package web_presentation.application_controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class FrontServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ApplicationController applicationController = getApplicationController(req);
    String commandString = (String) req.getParameter("command");
    DomainCommand comm = applicationController.getDomainCommand(commandString, getParameterMap(req));
    comm.run(getParameterMap(req));
    String viewPage = "/" + applicationController.getView(commandString, getParameterMap(req)) + ".jsp";

    forward(viewPage, req, resp);
  }

  protected ApplicationController getApplicationController(HttpServletRequest req) {
    return new AssetApplicationController();
  }

  protected Map getParameterMap(HttpServletRequest req) {
    return req.getParameterMap();
  }

  protected void forward(String target, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(target);
    dispatcher.forward(req, resp);
  }

}
