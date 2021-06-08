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
    // command : 반환(RETURN), 손상(DAMAGE)
    String commandString = (String) req.getParameter("command");
    DomainCommand comm = applicationController.getDomainCommand(commandString, getParameterMap(req));
    comm.run(req);
    String viewPage = "/view/" + applicationController.getView(commandString, getParameterMap(req)) + ".jsp";

//    System.out.println("DomainCommand : " + comm);
//    System.out.println("viewPage : " + viewPage);

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
