package offline_concurrency.pessimistic_offline_lock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
  void init(HttpServletRequest req, HttpServletResponse rsp);
  void process() throws Exception;
}
