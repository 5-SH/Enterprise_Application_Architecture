package domain_logic.table_module;

import basic.money.Money;
import domain_logic.transaction_script.MfDate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class Contract extends TableModule {
  private static Contract soleInstance = new Contract();

  public static Contract getInstance() { return soleInstance; }

  public static void initialize() {
    soleInstance = new Contract();
  }

  @Override
  protected Map getTable() {
    return getInstance().table;
  }

  @Override
  protected void doSet(Map recordSet) {
    getTable().put(Long.valueOf((String) recordSet.get("ID")), recordSet);
  }

  private Long getProductID(Long contractID) {
    return Long.valueOf((String) get(contractID).get("product"));
  }

  private MfDate getWhenSigned(Long contractID) {
    return new MfDate(Date.valueOf((String) get(contractID).get("dateSigned")));
  }

  public void calculateRecognitions(Long contractID) {
    Map contractRow = get(contractID);
    Money amount = Money.dollars(BigDecimal.valueOf(Long.valueOf((String) contractRow.get("revenue"))));
    RevenueRecognition rr = RevenueRecognition.getInstance();
    Product prod = Product.getInstance();
    Long prodID = getProductID(contractID);

    if (prod.getProductType(prodID).equals("W")) {
      rr.insert(contractID, amount, getWhenSigned(contractID));
    }
  }



}
