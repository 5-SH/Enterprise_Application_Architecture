package web_presentation.application_controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MovePageCommand implements DomainCommand {
  @Override
  public void run(HttpServletRequest req) {
    System.out.println("MovePageCommand run");
    req.setAttribute("command", "this is MovePageCommand");
  }

  @Override
  public String toString() {
    return "MovePageCommand{}";
  }
}
