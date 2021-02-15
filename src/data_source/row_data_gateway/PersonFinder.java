package data_source.row_data_gateway;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonFinder {
  private static final String findStatementString =
    "SELECT id, lastname, firstname, numberOfDependents FROM people WHERE id = ?";

  public PersonGateway find(Long id) {
    PersonGateway result = Registry.getPerson(id);
    if (result != null) return result;
    try {
      PreparedStatement stmt = Registry.DB().prepareStatement(findStatementString);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = PersonGateway.load(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  private static final String findResponsibleStatement =
    "SELECT id, lastname, firstname, numberOfDependents FROM people WHERE numberOfDependents > 0";

  public List findResponsibles() {
    List result = new ArrayList();
    try {
      PreparedStatement stmt = Registry.DB().prepareStatement(findResponsibleStatement);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result.add(PersonGateway.load(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }
}
