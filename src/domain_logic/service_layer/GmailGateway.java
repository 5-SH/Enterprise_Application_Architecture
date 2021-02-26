package domain_logic.service_layer;

public class GmailGateway implements EmailGateway {
  @Override
  public void sendEmailMessage(String toAddress, String subject, String body) {
    System.out.println("Send Email to " + toAddress + "\nSubject : " + subject + "\nbody : " + body);
  }
}
