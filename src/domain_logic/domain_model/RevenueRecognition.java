package domain_logic.domain_model;

import basic.money.Money;
import domain_logic.transaction_script.MfDate;

public class RevenueRecognition {
  private Money amount;
  private MfDate date;

  public RevenueRecognition(Money amount, MfDate date) {
    this.amount = amount;
    this.date = date;
  }

  public Money getAmount() {
    return amount;
  }

  boolean isRecognizableBy(MfDate asOf) {
    return asOf.after(date) || asOf.equals(date);
  }
}
