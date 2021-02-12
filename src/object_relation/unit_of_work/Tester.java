package object_relation.unit_of_work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Tester {
  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      Connection db = DriverManager.getConnection(url, props);

      AlbumMapper albumMapper = new AlbumMapper();
      albumMapper.setConnection(db);

      MapperRegistry mapperRegistry = new MapperRegistry();
      mapperRegistry.setMapper("object_relation.unit_of_work.Album", albumMapper);

//      List<String> titles = new ArrayList();
//      titles.add("title1");
//      titles.add("title2");
//      titles.add("title3");
//      EditAlbumScript.createAlbumByTitle(titles);

//      EditAlbumScript.updateTitle(new Long(1), "new title");

      EditAlbumScript.deleteTitle(new Long(3));
    } catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }
  }
}
