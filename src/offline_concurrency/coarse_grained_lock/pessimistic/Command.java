package offline_concurrency.coarse_grained_lock.pessimistic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
  void init(HttpServletRequest req, HttpServletResponse rsp);
  void process() throws Exception;
}
