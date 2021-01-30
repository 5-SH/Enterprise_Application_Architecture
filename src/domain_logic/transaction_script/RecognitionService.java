package domain_logic.transaction_script;

import basic_pattern.money.Money;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecognitionService {
  private Gateway db;

  public RecognitionService(Gateway db) {
    this.db = db;
  }

  public Money recognizedRevenue(long contractNumber, MfDate asOf) throws Exception {
    Money result = Money.dollars(0);
    try {
      ResultSet rs = db.findRecognitionsFor(contractNumber, asOf);
      while (rs.next()) {
        result = result.add(Money.dollars(rs.getBigDecimal("amount")));
      }
      return result;
    } catch (SQLException e) {
      throw new Exception(e);
    }
  }

  public void calculateRevenueRecognitions(long contractNumber) throws Exception {
    try {
      ResultSet contracts = db.findContract(contractNumber);
      contracts.next();
      Money totalRevenue = Money.dollars(contracts.getBigDecimal("revenue"));
      MfDate recognitionDate = new MfDate(contracts.getDate("dateSigned"));
      String type = contracts.getString("type");
      if (type.equals("S")) {
        Money[] allocation = totalRevenue.allocate(3);
        db.insertRecognition(contractNumber, allocation[0], recognitionDate);
        db.insertRecognition(contractNumber, allocation[1], recognitionDate.addDays(60));
        db.insertRecognition(contractNumber, allocation[2], recognitionDate.addDays(90));
      } else if (type.equals("W")) {
        db.insertRecognition(contractNumber, totalRevenue, recognitionDate);
      } else if (type.equals("D")) {
        Money[] allocation = totalRevenue.allocate(3);
        db.insertRecognition(contractNumber, allocation[0], recognitionDate);
        db.insertRecognition(contractNumber, allocation[1], recognitionDate.addDays(30));
        db.insertRecognition(contractNumber, allocation[2], recognitionDate.addDays(60));
      }
    } catch(SQLException e) {
      throw new Exception(e);
    }
  }
}
