package web_presentation.application_controller;

public class Response {
  private Class domainCommand;
  private String viewUrl;

  public Response(Class domainCommand, String viewUrl) {
    this.domainCommand = domainCommand;
    this.viewUrl = viewUrl;
  }

  public DomainCommand getDomainCommand() {
    DomainCommand result = null;
    try {
      result = (DomainCommand) domainCommand.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public String getViewUrl() {
    return viewUrl;
  }
}
