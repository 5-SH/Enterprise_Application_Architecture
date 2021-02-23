package domain_logic.table_module;

import basic.money.Money;
import domain_logic.transaction_script.MfDate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RevenueRecognition extends TableModule {
  private static RevenueRecognition soleInstance = new RevenueRecognition();

  public static RevenueRecognition getInstance() { return soleInstance; }

  public static void initialize() {
    soleInstance = new RevenueRecognition();
  }

  private static Long id = Long.valueOf(0);

  private static Long getNextID() {
    return ++id;
  }

  @Override
  protected Map getTable() {
    return getInstance().table;
  }

  @Override
  protected void doSet(Map recordSet) {
    getTable().put(recordSet.get("ID"), recordSet);
  }

  public Long insert(Long contractID, Money amount, MfDate date) {
    Map recordSet = new HashMap();
    Long id = getNextID();
    recordSet.put("ID", id);
    recordSet.put("contract", contractID);
    recordSet.put("amount", amount.amount());
    recordSet.put("recognizedOn", date);

    set(recordSet);
    return id;
  }

  public Money recognizedRevenue(Long contractID, MfDate asOf) {
    Money result = Money.dollars(0);
    for (Object source : table.keySet()) {
      Long key = Long.valueOf(source.toString());
      Map recordSet = get(key);

      if (recordSet.get("contract").equals(contractID)
        && ((MfDate) recordSet.get("recognizedOn")).before(asOf)) {
        result = result.add(Money.dollars((BigDecimal) recordSet.get("amount")));
      }
    }
    return result;
  }
}
