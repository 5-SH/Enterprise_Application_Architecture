package web_presentation.application_controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ErrorCommand implements DomainCommand {
  @Override
  public void run(HttpServletRequest req) {
    System.out.println("ErrorCommand run");
    req.setAttribute("command", "this is ErrorCommand");
  }

  @Override
  public String toString() {
    return "ErrorCommand{}";
  }
}
