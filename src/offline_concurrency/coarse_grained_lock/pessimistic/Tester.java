package offline_concurrency.coarse_grained_lock.pessimistic;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Tester {
  public static void main(String[] args) {
    try {
      String webappDirLocation = "webapp/";
      Tomcat tomcat = new Tomcat();
      String webPort = System.getenv("PORT");
      if(webPort == null || webPort.isEmpty()) {
        webPort = "8080";
      }
      tomcat.setPort(Integer.valueOf(webPort));
      Connector connector = tomcat.getConnector();
      connector.setURIEncoding("UTF-8");
      tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
      System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());
      tomcat.start();
      tomcat.getServer().await();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
