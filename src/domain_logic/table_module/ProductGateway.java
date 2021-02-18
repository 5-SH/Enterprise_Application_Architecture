package domain_logic.table_module;

public class ProductGateway extends TableGateway {
  @Override
  protected String getFindAllStatement() {
    return "SELECT * FROM products";
  }

  @Override
  protected String getFindRowStatement() {
    return "SELECT * FROM products WHERE id = ?";
  }
}
