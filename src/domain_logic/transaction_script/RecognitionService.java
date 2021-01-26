package domain_logic.transaction_script;

import basic_pattern.Money;
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
}
