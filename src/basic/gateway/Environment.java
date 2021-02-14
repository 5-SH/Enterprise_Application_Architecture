package basic.gateway;

public class Environment {
  public static MessageGateway messageGateway;

  public static MessageGateway getMessageGateway() {
    return messageGateway;
  }

  public static void testInit() {
    messageGateway = new MessageGatewayStub();
  }

}
