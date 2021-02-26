package domain_logic.service_layer;

import domain_logic.transaction_script.MfDate;

public class Tester {
  public static void main(String[] args) {
    EmailGateway gmailGateway = new GmailGateway();
    IntergrationGateway thirdPlatformGateway = new ThirdPlatformGateway();

    RecognitionService recognitionService = new RecognitionService(gmailGateway, thirdPlatformGateway);
    recognitionService.calculateRevenueRecognitions(Long.valueOf(1));
    recognitionService.recognizedRevenue(Long.valueOf(1), new MfDate(2020, 12, 31));
  }
}
