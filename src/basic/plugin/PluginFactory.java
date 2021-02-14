package basic.plugin;

import java.io.FileInputStream;
import java.util.Properties;

public class PluginFactory {
  private static Properties props = new Properties();

  static {
    try {
      String path = "src/basic/plugin/";
      FileInputStream sysPropsFile = sysPropsFile = new FileInputStream(path + "system.properties");
      Properties sysProps = new Properties(System.getProperties());
      sysProps.load(sysPropsFile);
      System.setProperties(sysProps);

      String propsFile = System.getProperty("plugins");
      props.load(new FileInputStream(path + propsFile));
    } catch (Exception e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public static Object getPlugin(Class iface) {
    String implName = props.getProperty(iface.getName());
    if (implName == null) {
      throw new RuntimeException("implementation not specified for " + iface.getName() + " in PluginFactory properties.");
    }
    try {
      return Class.forName(implName).getConstructor().newInstance();
    } catch (Exception e) {
      throw new RuntimeException("factory unable to construct instance of " + iface.getName());
    }
  }
}
