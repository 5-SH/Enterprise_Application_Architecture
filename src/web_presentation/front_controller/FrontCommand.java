package web_presentation.front_controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {
  protected ServletContext context;
  protected HttpServletRequest request;
  protected HttpServletResponse response;

  public void init(ServletContext ctxt, HttpServletRequest req, HttpServletResponse resp) {
    this.context = ctxt;
    this.request = req;
    this.response = resp;
  }

  abstract public void process() throws ServletException, IOException;

  protected void forward(String target) throws ServletException, IOException {
    RequestDispatcher dispatcher = context.getRequestDispatcher(target);
    dispatcher.forward(request, response);
  }
}
