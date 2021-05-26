package web_presentation.page_controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

  public static List findByArtistID(Long artistID) {
    List<Album> result = new ArrayList<Album>();
    try {
      String sql = "SELECT id, title, artistID FROM album WHERE artistID = ?";
      PreparedStatement stmt = Registry.DB().prepareStatement(sql);
      stmt.setLong(1, artistID);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result.add(load(rs));
      }
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

  // transform view
  public String toSampleXmlDocument() {
    return "<album>" +
      "<title>Stormcock</title>" +
      "<artist>Roy Harper</artist>" +
      "<trackList>" +
      "<track><title>Hors d'Oeuvres</title><time>8:37</time></track>" +
      "<track><title>The Same Old Rock</title><time>12:24</time></track>" +
      "<track><title>Me and My Woman</title><time>13:01</time></track>" +
      "</trackList>" +
      "</album>";
  }
}
