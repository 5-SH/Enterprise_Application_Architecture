package web_presentation.page_controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Album extends DomainObject {
  private String title;
  private Long artistID;

  public Album(Long id, String title, Long artistID) {
    super(id);
    this.title = title;
    this.artistID = artistID;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getArtistID() {
    return artistID;
  }

  public void setArtistID(Long artistID) {
    this.artistID = artistID;
  }

  public static Album findByTitle(String title) {
    Album result = null;
    try {
      String sql = "SELECT id, title, artistID FROM album WHERE title = ?";
      PreparedStatement stmt = Registry.DB().prepareStatement(sql);
      stmt.setString(1, title);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = load(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  private static Album load(ResultSet rs) throws SQLException {
    Long id = new Long(rs.getLong(1));
    String title = rs.getString(2);
    Long artistID = new Long(rs.getLong(3));
    return new Album(id, title, artistID);
  }
}
