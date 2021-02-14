package basic.service_stub;

import basic.money.Money;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class TestTaxService implements TaxService {
  private static Set exemptions = new HashSet();
  private static final BigDecimal EXEMPT_RATE = new BigDecimal("0.0000");
  private static final BigDecimal FLAT_RATE = new BigDecimal("0.0500");

  public TaxInfo getSalesTaxInfo(String productCode, Address addr, Money saleAmount) {
    BigDecimal rate = getRate(productCode, addr);
    return new TaxInfo(rate, saleAmount.multiply(rate));
  }

  private static String getExemptionKey(String productCode, String stateCode) {
    return productCode + "_" + stateCode;
  }

  public static void addExemption(String productCode, String stateCode) {
    exemptions.add(getExemptionKey(productCode, stateCode));
  }

  public static void reset() {
    exemptions.clear();
  }

  private static BigDecimal getRate(String productCode, Address addr) {
    if (exemptions.contains(getExemptionKey(productCode, addr.getStateCode()))) {
      return EXEMPT_RATE;
    } else {
      return FLAT_RATE;
    }
  }
}
