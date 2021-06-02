package web_presentation.two_step_view;

import web_presentation.front_controller.FrontCommand;
import web_presentation.front_controller.UnknownCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    FrontCommand command = getCommand(req);
    command.init(getServletContext(), req, resp);
    command.process();
  }

  private FrontCommand getCommand(HttpServletRequest req) {
    try {
      return (FrontCommand) getCommandClass(req).newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private Class getCommandClass(HttpServletRequest req) {
    Class result;
    final String commandClassName = "web_presentation.two_step_view." + (String) req.getParameter("command") + "Command";
    try {
      result = Class.forName(commandClassName);
    } catch (ClassNotFoundException e) {
      result = UnknownCommand.class;
    }
    return result;
  }
}
