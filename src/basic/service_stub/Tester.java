package basic.service_stub;

import basic.money.Money;

public class Tester {
  public static void main(String[] args) {
    BillingSchedule billingSchedule = new BillingSchedule("12300", new Address("IL"), Money.dollars(100));
    ChargeGenerator chargeGenerator = new ChargeGenerator();

//    TestTaxService.addExemption("12300", "IL");
    Charge[] charges = chargeGenerator.calculateCharges(billingSchedule);
    for (int i = 0; i < charges.length; i++) {
      System.out.println(charges[i].getBillingAmount().amount());
    }
  }
}
