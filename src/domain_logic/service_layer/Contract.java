package domain_logic.service_layer;

import basic.money.Money;
import domain_logic.transaction_script.MfDate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Active Record
public class Contract extends DomainObject {
  private Long product;
  private Money revenue;
  private MfDate dateSigned;
  private Money revenueRecognition;

  public Contract(Long id, Long product, Money revenue, MfDate dateSigned) {
    super(id);
    this.product = product;
    this.revenue = revenue;
    this.dateSigned = dateSigned;
    this.revenueRecognition = Money.dollars(0);
  }

  private final static String findStatementString = "SELECT ID, product, revenue, dateSigned FROM contracts WHERE id = ?";

  public static Contract find(Long id) {
    Contract result = Registry.getContract(id);
    if (result != null) return result;
    try {
      PreparedStatement stmt = Registry.DB().prepareStatement(findStatementString);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = load(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  public static Contract find(long id) { return find(new Long(id)); }

  public static Contract load(ResultSet rs) throws SQLException {
    Long id = new Long(rs.getLong(1));
    Contract result = Registry.getContract(id);
    if (result != null) return result;

    Long product = rs.getLong(2);
    Money revenue = Money.dollars(rs.getBigDecimal(3));
    MfDate dateSigned = new MfDate(rs.getDate(4));

    result = new Contract(id, product, revenue, dateSigned);
    Registry.addContract(result);

    return result;
  }

  public static Contract read(Long contractID) {
    return find(contractID);
  }

  public static Contract readForUpdate(Long contractID) {
    return find(contractID);
  }

  public void calculateRecognitions() {
    revenueRecognition = revenue;
  }

  public String getAdministratorEmailAddress() {
    return "Administrator@gmail.com";
  }

  public Money recognizedRevenue(MfDate asOf) {
    return revenueRecognition;
  }

  @Override
  public String toString() {
    return "Contract{" +
      "product=" + product +
      ", revenue=" + revenue.amount() +
      ", dateSigned=" + dateSigned +
      ", revenueRecognition=" + revenueRecognition.amount() +
      '}';
  }
}
