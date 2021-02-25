package domain_logic.service_layer;

public class ApplicationService {
  private EmailGateway emailGateway;
  private IntergrationGateway intergrationGateway;

  public ApplicationService(EmailGateway emailGateway, IntergrationGateway intergrationGateway) {
    this.emailGateway = emailGateway;
    this.intergrationGateway = intergrationGateway;
  }

  protected EmailGateway getEmailGateway() {
    return emailGateway;
  }

  protected IntergrationGateway getIntergrationGateway() {
    return intergrationGateway;
  }
}
