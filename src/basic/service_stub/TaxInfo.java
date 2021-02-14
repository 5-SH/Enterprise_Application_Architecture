package basic.service_stub;

import basic.money.Money;

import java.math.BigDecimal;

public class TaxInfo {
  BigDecimal stateRate;
  Money stateAmount;

  public TaxInfo(BigDecimal stateRate, Money stateAmount) {
    this.stateRate = stateRate;
    this.stateAmount = stateAmount;
  }

  public BigDecimal getStateRate() {
    return stateRate;
  }

  public Money getStateAmount() {
    return stateAmount;
  }
}
