package basic.service_stub;

import basic.money.Money;

import java.math.BigDecimal;

public class FlatRateTaxService implements TaxService {
  private static final BigDecimal FLAT_RATE = new BigDecimal("0.0500");

  @Override
  public TaxInfo getSalesTaxInfo(String productCode, Address addr, Money saleAmount) {
    return new TaxInfo(FLAT_RATE, saleAmount.multiply(FLAT_RATE));
  }
}
