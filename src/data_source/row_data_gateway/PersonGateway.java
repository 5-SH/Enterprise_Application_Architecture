package data_source.row_data_gateway;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonGateway extends DomainObject {
  private String lastName;
  private String firstName;
  private int numberOfDependents;

  public PersonGateway(Long id, String lastName, String firstName, int numberOfDependents) {
    super(id);
    this.lastName = lastName;
    this.firstName = firstName;
    this.numberOfDependents = numberOfDependents;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public int getNumberOfDependents() {
    return numberOfDependents;
  }

  public void setNumberOfDependents(int numberOfDependents) {
    this.numberOfDependents = numberOfDependents;
  }

  private static final String updateStatementString = "UPDATE people SET lastname = ?, firstname = ?, numberOfDependents = ? WHERE id = ?";

  public void update() {
    try {
      PreparedStatement stmt = Registry.DB().prepareStatement(updateStatementString);
      stmt.setString(1, lastName);
      stmt.setString(2, firstName);
      stmt.setLong(3, numberOfDependents);
      stmt.setLong(4, getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static final String insertStatementString = "INSERT INTO people VALUES (?, ?, ?, ?)";

  public Long insert() {
    try {
      PreparedStatement stmt = Registry.DB().prepareStatement(insertStatementString);
      stmt.setLong(1, getId());
      stmt.setString(2, lastName);
      stmt.setString(3, firstName);
      stmt.setLong(4, numberOfDependents);
      stmt.executeUpdate();
      Registry.addPerson(this);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return getId();
  }

  public static PersonGateway load(ResultSet rs) throws SQLException {
    Long id = new Long(rs.getLong(1));
    PersonGateway result = (PersonGateway) Registry.getPerson(id);
    if (result != null) return result;

    String lastName = rs.getString(2);
    String firstName = rs.getString(3);
    int numberOfDependents = rs.getInt(4);
    result = new PersonGateway(id, lastName, firstName, numberOfDependents);
    Registry.addPerson(result);

    return result;
  }
}
