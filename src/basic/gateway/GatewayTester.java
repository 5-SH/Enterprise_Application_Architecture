package basic.gateway;

public class GatewayTester {
  public void testSendNullArg() {
    try {
      gate().sendConfirmation(null, 5, "US");
      System.out.println("Didn't detect null argument");
    } catch (NullPointerException expected) {
      System.out.println("Detect null argument");
    }
    assert gate().getNumberOfMeesagesSent() == 0 : "Sent message count is 0";
  }

  private MessageGatewayStub gate() {
    return (MessageGatewayStub) Environment.getMessageGateway();
  }

  protected void setUp() throws Exception {
    Environment.testInit();
  }
}
