package domain_logic.table_module;

import java.util.Map;

public class Product extends TableModule {
  private static Product soleInstance = new Product();

  public static Product getInstance() { return soleInstance; }

  public static void initialize() {
    soleInstance = new Product();
  }

  @Override
  protected Map getTable() {
    return getInstance().table;
  }

  @Override
  protected void doSet(Map recordSet) {
    getTable().put(Long.valueOf((String) recordSet.get("ID")), recordSet);
  }

  public String getProductType(Long id) {
    return (String) get(id).get("type");
  }

}
