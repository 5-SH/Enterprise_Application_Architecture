package offline_concurrency.optimistic_offline_lock;

import java.sql.SQLException;

public class AbstractMapper {
  private String table;
  private String[] colmuns;

  private String loadSQL;
  private String deleteSQL;
  private String insertSQL;
  private String updateSQL;

  public AbstractMapper(String table, String[] columns) {
    this.table = table;
    this.colmuns = columns;
    buildStatements();
  }

  public DomainObject find(Long id) {
    return null;
  }

  protected void throwConcurrencyException(DomainObject object) throws SQLException {

  }

  private void buildStatements() {
    int columnsLength = colmuns.length;

    this.loadSQL = "SELECT * FROM " + this.table + " WHERE id = ?";

    this.deleteSQL = "DELETE FROM " + this.table + " WHERE id =? and version = ?";

    this.insertSQL = "INSERT INTO " + this.table + " VALUES (";
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < columnsLength; i++) {
      buffer.append("?, ");
    }
    buffer.setLength(buffer.length() - 2);
    this.insertSQL += (buffer.toString() + ");");

    this.updateSQL = "UPDATE " + this.table + " SET ";
    buffer = new StringBuffer();
    for (int i = 0; i < columnsLength; i++) {
      buffer.append(this.colmuns[i] + " = ?,");
    }
    buffer.setLength(buffer.length() - 1);
    this.updateSQL += (buffer.toString() + ";");
  }
}
