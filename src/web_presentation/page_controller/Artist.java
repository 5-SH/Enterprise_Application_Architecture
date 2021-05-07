package web_presentation.page_controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Artist extends DomainObject {
  private String name;

  public Artist(Long id, String name) {
    super(id);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static Artist findByName(String name) {
    Artist result = null;
    try {
      String sql = "SELECT id, name FROM artists WHERE name = ?";
      PreparedStatement stmt = Registry.DB().prepareStatement(sql);
      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = load(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  private static Artist load(ResultSet rs) throws SQLException {
    Long id = new Long(rs.getLong(1));
    String name = rs.getString(2);
    return new Artist(id, name);
  }

  @Override
  public String toString() {
    return "Artist{" +
      "id='" + getId() + '\'' +
      "name='" + name + '\'' +
      '}';
  }
}
