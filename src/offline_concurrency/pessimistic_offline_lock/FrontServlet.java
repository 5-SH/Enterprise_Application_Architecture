package offline_concurrency.pessimistic_offline_lock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Command command = getCommand(req);
    command.init(req, resp);
    try {
      command.process();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Command getCommand(HttpServletRequest req) {
    try {
      return new TransactionalCommand((Command) getCommandClass(req).newInstance());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private Class getCommandClass(HttpServletRequest req) {
    Class result = null;
    final String commandClassName = "offline_concurrency.pessimistic_offline_lock." + (String) req.getParameter("command") + "Command";
    try {
      result = Class.forName(commandClassName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
