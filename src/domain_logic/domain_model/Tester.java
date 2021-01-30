package domain_logic.domain_model;

import basic_pattern.money.Money;
import domain_logic.transaction_script.MfDate;

public class Tester {
  private static Product word = Product.newWordProcessor("Thinking Word");
  private static Product calc = Product.newSpreadsheet("Thinking Calc");
  private static Product db = Product.newDatabase("Thinking DB");

  public static void main(String[] args) {
    Contract wordContract = new Contract(word, Money.dollars(300), new MfDate(2020, 12, 31));
    wordContract.calculateRecognitions();
    Money revenue = wordContract.recognizedRevenue(new MfDate(2023, 12, 31));
    System.out.println(revenue.amount());
  }
}
