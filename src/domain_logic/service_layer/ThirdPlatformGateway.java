package domain_logic.service_layer;

public class ThirdPlatformGateway implements IntergrationGateway {
  @Override
  public void publishRevenueRecognitionCalculation(Contract contract) {
    System.out.println("Publish contract to third platform\n" + contract.toString());
  }
}
