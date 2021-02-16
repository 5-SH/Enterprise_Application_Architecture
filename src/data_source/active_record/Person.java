package data_source.active_record;

import basic.money.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person extends DomainObject {
  private String lastName;
  private String firstName;
  private int numberOfDependents;

  private final static String findStatementString = "SELECT id, lastname, firstName, numberOfDependents FROM people WHERE id = ?";

  public Person(Long id, String lastName, String firstName, int numberOfDependents) {
    super(id);
    this.lastName = lastName;
    this.firstName = firstName;
    this.numberOfDependents = numberOfDependents;
  }

  public static Person find(Long id) {
    Person result = Registry.getPerson(id);
    if (result != null) return result;
    try {
      PreparedStatement stmt = Registry.DB().prepareStatement(findStatementString);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = load(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  public static Person find(long id) {
    return find(new Long(id));
  }

  public static Person load(ResultSet rs) throws SQLException {
    Long id = new Long(rs.getLong(1));
    Person result = Registry.getPerson(id);
    if (result != null) return result;

    String lastName = rs.getString(2);
    String firstName = rs.getString(3);
    int numberOfDependents = rs.getInt(4);
    result = new Person(id, lastName, firstName, numberOfDependents);
    Registry.addPerson(result);

    return result;
  }

  private final static String updateStatementString = "UPDATE people " +
    " SET lastname = ?, firstName = ?, numberOfDependents = ?" +
    " WHERE id = ?";

  public void update() {
    try {
      PreparedStatement stmt = Registry.DB().prepareStatement(updateStatementString);
      stmt.setString(1, lastName);
      stmt.setString(2, firstName);
      stmt.setInt(3, numberOfDependents);
      stmt.setLong(4, getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private final static String insertStatementString = "INSERT INTO people VALUES (?, ?, ?, ?)";

  public Long insert() {
    try {
      PreparedStatement stmt = Registry.DB().prepareStatement(insertStatementString);
      stmt.setLong(1, getId());
      stmt.setString(2, lastName);
      stmt.setString(3, firstName);
      stmt.setInt(4, numberOfDependents);
      stmt.execute();

      Registry.addPerson(this);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return getId();
  }

  // 도메인 논리
  public Money getExemption() {
    Money baseExemption = Money.dollars(1500);
    Money dependentExemption = Money.dollars(750);
    return baseExemption.add(dependentExemption.multiply(numberOfDependents));
  }
}
