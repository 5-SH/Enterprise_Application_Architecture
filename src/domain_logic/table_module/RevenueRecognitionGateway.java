package domain_logic.table_module;

public class RevenueRecognitionGateway extends TableGateway {
  @Override
  protected String getFindAllStatement() {
    return "SELECT * FROM revenuerecognitions";
  }

  @Override
  protected String getFindRowStatement() {
    return "SELECT * FROM revenuerecognitions WHERE ID = ?";
  }
}
