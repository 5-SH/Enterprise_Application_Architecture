package web_presentation.application_controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ReturnAssetCommand implements DomainCommand {
  @Override
  public void run(HttpServletRequest req) {
    System.out.println("ReturnAssetCommand run");
    req.setAttribute("command", "this is ReturnAssetCommand");
  }

  @Override
  public String toString() {
    return "ReturnAssetCommand{}";
  }
}
