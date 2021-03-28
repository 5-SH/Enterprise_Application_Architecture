package object_relation.structure.serialized_lob;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customer extends DomainObject {
  private String name;
  private List departments = new ArrayList();

  public Customer(Long id, String name, List departments) {
    super(id);
    this.name = name;
    this.departments = departments;
  }

  private Long findNextDatabaseId() {
    PreparedStatement stmt = null;
    Long result = null;
    try {
      stmt = Registry.DB().prepareStatement("SELECT IFNULL(MAX(id) + 1, 0) AS nextID FROM customers");
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = rs.getLong("nextID");
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  private final static String insertStatementString = "INSERT INTO customers VALUES (?, ?, ?);";

  public Long insert() {
    PreparedStatement stmt = null;
    try {
      stmt = Registry.DB().prepareStatement(insertStatementString);
      setId(findNextDatabaseId());
      stmt.setInt(1, getId().intValue());
      stmt.setString(2, name);
//      stmt.setString(3. )
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
