package domain_logic.table_module;

import basic.money.Money;
import domain_logic.transaction_script.MfDate;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class ContractGateway extends TableGateway {
  @Override
  protected String getFindAllStatement() {
    return "SELECT * FROM contracts";
  }

  @Override
  protected String getFindRowStatement() {
    return "SELECT * FROM contracts WHERE id = ?";
  }

  @Override
  protected String getInsertStatement() {
    return "INSERT INTO contracts VALUES (?, ?, ?, ?)";
  }

  @Override
  protected void doInsert(Map<String, String> recordSet, PreparedStatement insertStatement) throws SQLException {
    Long ID = Long.valueOf(recordSet.get("ID"));
    Long contract = Long.valueOf(recordSet.get("product"));
    Money revenue = Money.dollars(BigDecimal.valueOf(Long.valueOf(recordSet.get("revenue"))));
    MfDate dateSigned = new MfDate(Date.valueOf(recordSet.get("dateSigned")));

    insertStatement.setLong(1, ID);
    insertStatement.setLong(2, contract);
    insertStatement.setBigDecimal(3, revenue.amount());
    insertStatement.setDate(4, dateSigned.toSqlDate());

    insertStatement.executeUpdate();
  }
}
