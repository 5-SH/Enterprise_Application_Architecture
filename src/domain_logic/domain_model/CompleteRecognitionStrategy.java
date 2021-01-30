package domain_logic.domain_model;

public class CompleteRecognitionStrategy extends RecognitionStrategy {
  @Override
  public void calculateRevenueRecognitions(Contract contract) {
    contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue(), contract.getWhenSigned()));
  }
}
