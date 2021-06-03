package web_presentation.application_controller;

import java.util.Map;

public interface DomainCommand {
  public void run(Map params);
}
