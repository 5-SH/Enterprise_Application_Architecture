package basic.service_stub;

import basic.money.Money;

public class BillingSchedule {
  private String product;
  private Address address;
  private Money billingAmount;

  public BillingSchedule(String product, Address address, Money billingAmount) {
    this.product = product;
    this.address = address;
    this.billingAmount = billingAmount;
  }

  public String getProduct() {
    return product;
  }

  public Address getAddress() {
    return address;
  }

  public Money getBillingAmount() {
    return billingAmount;
  }
}
