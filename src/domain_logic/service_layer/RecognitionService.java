package domain_logic.service_layer;

import basic.money.Money;
import domain_logic.transaction_script.MfDate;

public class RecognitionService extends ApplicationService {
  public RecognitionService(EmailGateway emailGateway, IntergrationGateway intergrationGateway) {
    super(emailGateway, intergrationGateway);
  }

  public void calculateRevenueRecognitions(Long contractID) {
    Contract contract = Contract.readForUpdate(contractID);
    contract.calculateRecognitions();
    getEmailGateway().sendEmailMessage(
      contract.getAdministratorEmailAddress(),
      "Contract #" + contractID,
      contract + " has had revenue recognitions calculated.");

    getIntergrationGateway().publishRevenueRecognitionCalculation(contract);
  }

  public Money recognizedRevenue(Long contractID, MfDate asOf) {
    return Contract.read(contractID).recognizedRevenue(asOf);
  }
}
