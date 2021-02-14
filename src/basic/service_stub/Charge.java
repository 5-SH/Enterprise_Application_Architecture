package basic.service_stub;

import basic.money.Money;

public class Charge {
  private Money billingAmount;
  private boolean isTax;

  public Charge(Money billingAmount, boolean isTax) {
    this.billingAmount = billingAmount;
    this.isTax = isTax;
  }

  public Money getBillingAmount() {
    return billingAmount;
  }
}
