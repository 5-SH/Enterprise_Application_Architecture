package domain_logic.table_module;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class ProductGateway extends TableGateway {
  @Override
  protected String getFindAllStatement() {
    return "SELECT * FROM products";
  }

  @Override
  protected String getFindRowStatement() {
    return "SELECT * FROM products WHERE id = ?";
  }

  @Override
  protected String getInsertStatement() {
    return "INSERT INTO products VALUES (?, ?, ?)";
  }

  @Override
  protected void doInsert(Map<String, String> recordSet, PreparedStatement insertStatement) throws SQLException {
    Long ID = Long.valueOf(recordSet.get("ID"));
    String name = recordSet.get("name");
    String type = recordSet.get("type");

    insertStatement.setLong(1, ID);
    insertStatement.setString(2, name);
    insertStatement.setString(3, type);

    insertStatement.executeUpdate();
  }
}
