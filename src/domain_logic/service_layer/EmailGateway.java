package domain_logic.service_layer;

public interface EmailGateway {
  void sendEmailMessage(String toAddress, String subject, String body);
}
