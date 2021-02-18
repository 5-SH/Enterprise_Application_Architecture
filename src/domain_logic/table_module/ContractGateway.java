package domain_logic.table_module;

public class ContractGateway extends TableGateway {
  @Override
  protected String getFindAllStatement() {
    return "SELECT * FROM contracts";
  }

  @Override
  protected String getFindRowStatement() {
    return "SELECT * FROM contracts WHERE id = ?";
  }

  // ResultSet to RecordSet(Map)
}
