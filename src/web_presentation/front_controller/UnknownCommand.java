package web_presentation.front_controller;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {
  @Override
  public void process() throws ServletException, IOException {
    forward("/view/unknown.jsp");
  }
}
