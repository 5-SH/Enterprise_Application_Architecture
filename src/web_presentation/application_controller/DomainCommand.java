package web_presentation.application_controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface DomainCommand {
  public void run(HttpServletRequest params);
}
