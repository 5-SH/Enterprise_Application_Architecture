package data_source.active_record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Registry {
  private static Registry soleInstance = new Registry();
  protected Map loadedPerson;
  protected Connection db;

  public Registry() {
    loadedPerson = new HashMap();
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      db = DriverManager.getConnection(url, props);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static Registry getInstance() { return soleInstance; }

  public static Connection DB() { return getInstance().db; }

  public static void initialize() {
    soleInstance = new Registry();
  }

  public static void addPerson(Person person) {
    if (!getInstance().loadedPerson.containsKey(person.getId()))
      getInstance().loadedPerson.put(person.getId(), person);
  }

  public static Person getPerson(Long id) {
    return (Person) getInstance().loadedPerson.get(id);
  }
}
