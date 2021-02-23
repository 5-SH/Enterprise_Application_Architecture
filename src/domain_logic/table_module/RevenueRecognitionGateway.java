package domain_logic.table_module;

import basic.money.Money;
import domain_logic.transaction_script.MfDate;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class RevenueRecognitionGateway extends TableGateway {
  @Override
  protected String getFindAllStatement() {
    return "SELECT * FROM revenuerecognitions";
  }

  @Override
  protected String getFindRowStatement() {
    return "SELECT * FROM revenuerecognitions WHERE ID = ?";
  }

  @Override
  protected String getInsertStatement() {
    return "INSERT INTO revenuerecognitions VALUES (?, ?, ?, ?)";
  }

  @Override
  protected void doInsert(Map recordSet, PreparedStatement insertStatement) throws SQLException {
    Long ID = (Long) recordSet.get("ID");
    Long contract = (Long) recordSet.get("contract");
    Money amount = Money.dollars((BigDecimal) recordSet.get("amount"));
    MfDate recognizedOn = (MfDate) recordSet.get("recognizedOn");

    insertStatement.setLong(1, ID);
    insertStatement.setLong(2, contract);
    insertStatement.setBigDecimal(3, amount.amount());
    insertStatement.setDate(4, recognizedOn.toSqlDate());

    insertStatement.executeUpdate();
  }
}
