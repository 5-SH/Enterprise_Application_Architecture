package domain_logic.table_module;

import basic.money.Money;
import domain_logic.transaction_script.MfDate;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tester {
  public static void main(String[] args) {
    try {
      ProductGateway productGateway = new ProductGateway();
      List<Map> productList = TableGateway.convert(productGateway.findAll());
      Product.getInstance().setAll(productList);

      ContractGateway contractGateway = new ContractGateway();
      List<Map> list = TableGateway.convert(contractGateway.findRow(Long.valueOf(2)));
      for (Map<String, String> recordSet : list) {
        Contract.getInstance().set(recordSet);
      }

      Contract.getInstance().calculateRecognitions(Long.valueOf(2));
      System.out.println(RevenueRecognition.getInstance().table.toString());

      RevenueRecognitionGateway revenueRecognitionGateway = new RevenueRecognitionGateway();
      for (Object source : RevenueRecognition.getInstance().table.keySet()) {
        Long key = Long.valueOf(source.toString());
        System.out.println(RevenueRecognition.getInstance().table.get(key).toString());
        revenueRecognitionGateway.insert((Map) RevenueRecognition.getInstance().table.get(key));
      }

      Money result = RevenueRecognition.getInstance().recognizedRevenue(new Long(2), new MfDate(2021, 2, 31));
      System.out.println(result.amount());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
