package offline_concurrency.implicit_lock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
  void init(HttpServletRequest req, HttpServletResponse rsp);
  void process() throws Exception;
}
