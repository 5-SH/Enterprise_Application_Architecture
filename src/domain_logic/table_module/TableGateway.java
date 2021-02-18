package domain_logic.table_module;

import java.sql.*;
import java.util.*;

public abstract class TableGateway {
  private Connection db;

  public TableGateway() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      this.db = DriverManager.getConnection(url, props);
    } catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }
  }

  public static List<Map> convert(ResultSet rs) throws SQLException {
    ResultSetMetaData metaData = rs.getMetaData();
    int size = metaData.getColumnCount();

    List<Map> list = new ArrayList<Map>();
    Map<String, Object> map;
    String column;
    while (rs.next()) {
      map = new HashMap<String, Object>();
      for (int i = 0; i < size; i++) {
        column = metaData.getColumnName(i + 1);
        map.put(column, rs.getString(column));
      }
      list.add(map);
    }
    return list;
  }

  public ResultSet findAll() throws SQLException {
    PreparedStatement stmt = db.prepareStatement(getFindAllStatement());
    ResultSet result = stmt.executeQuery();
    return result;
  }

  protected abstract String getFindAllStatement();

  public ResultSet findRow(Long key) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(getFindRowStatement());
    stmt.setLong(1, key);
    ResultSet result = stmt.executeQuery();
    return result;
  }

  protected abstract String getFindRowStatement();
}
