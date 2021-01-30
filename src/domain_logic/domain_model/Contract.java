package domain_logic.domain_model;

import basic_pattern.money.Money;
import domain_logic.transaction_script.MfDate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Contract {
  private List revenueRecognitions = new ArrayList();
  private Product product;
  private Money revenue;
  private MfDate whenSigned;
  private Long id;

  public Contract(Product product, Money revenue, MfDate whenSigned) {
    this.product = product;
    this.revenue = revenue;
    this.whenSigned = whenSigned;
  }

  public Money recognizedRevenue(MfDate asOf) {
    Money result = Money.dollars(0);
    Iterator it = revenueRecognitions.iterator();
    while (it.hasNext()) {
      RevenueRecognition r = (RevenueRecognition) it.next();
      if (r.isRecognizableBy(asOf)) result = result.add(r.getAmount());
    }

    return result;
  }

  public void addRevenueRecognition(RevenueRecognition revenueRecognition) {
    revenueRecognitions.add(revenueRecognition);
  }

  public void calculateRecognitions() {
    product.calculateRevenueRecognitions(this);
  }

  public Money getRevenue() {
    return revenue;
  }

  public MfDate getWhenSigned() {
    return whenSigned;
  }
}
