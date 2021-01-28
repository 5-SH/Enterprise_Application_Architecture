package domain_logic.transaction_script;

import basic_pattern.Money;

public class TransactionScriptTest {
  public static void main(String[] args) throws Exception {
    Gateway gateway = new Gateway();
    RecognitionService recognitionService = new RecognitionService(gateway);

    recognitionService.calculateRevenueRecognitions(1);

    Money revenue = recognitionService.recognizedRevenue(1, new MfDate(2023, 12, 31));
    System.out.println(revenue.amount());
  }
}
