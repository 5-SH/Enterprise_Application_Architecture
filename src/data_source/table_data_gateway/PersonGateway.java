package data_source.table_data_gateway;

import java.sql.*;
import java.util.Properties;

public class PersonGateway {
  private Connection db;

  public PersonGateway() {
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

  public ResultSet FindAll() throws SQLException {
    String sql = "select * from person";
    PreparedStatement stmt = db.prepareStatement(sql);
    ResultSet result = stmt.executeQuery();
    return result;
  }

  public ResultSet FindWithLastName(String lastName) throws SQLException {
    String sql = "SELECT * FROM person WHERE lastname = ?";
    PreparedStatement stmt = db.prepareStatement(sql);
    stmt.setString(1, lastName);
    ResultSet result = stmt.executeQuery();
    return result;
  }

  public ResultSet FindWhere(String whereClause) throws SQLException {
    String sql = "select * from person where " + whereClause;
    PreparedStatement stmt = db.prepareStatement(sql);
    ResultSet result = stmt.executeQuery();
    return result;
  }

  public ResultSet FindRow(long key) throws SQLException {
    String sql = "SELECT * FROM person WHERE id = ?";
    PreparedStatement stmt = db.prepareStatement(sql);
    stmt.setLong(1, key);
    ResultSet result = stmt.executeQuery();
    return result;
  }

  public void Update (long key, String lastname, String firstname, long numberOfDependents) throws SQLException {
    String sql =
      "UPDATE person " +
        "SET lastname = ?, firstname = ?, numberOfDependents = ? " +
        "WHERE id = ?";
    PreparedStatement stmt = db.prepareStatement(sql);
    stmt.setString(1, lastname);
    stmt.setString(2, firstname);
    stmt.setLong(3, numberOfDependents);
    stmt.setLong(4, key);
    stmt.executeUpdate();
  }

  public void Insert(String lastname, String firstname, long numberOfDependents) throws SQLException {
    String sql = "INSERT INTO person (lastname, firstname, numberOfDependents) VALUES (?, ?, ?)";
    PreparedStatement stmt = db.prepareStatement(sql);
    // key auto increase
    stmt.setString(1, lastname);
    stmt.setString(2, firstname);
    stmt.setLong(3, numberOfDependents);
    stmt.executeUpdate();
  }

  public void Delete(long key) throws SQLException {
    String sql = "DELETE FROM person WHERE id = ?";
    PreparedStatement stmt = db.prepareStatement(sql);
    stmt.setLong(1, key);
    stmt.executeUpdate();
  }
}
