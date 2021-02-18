package domain_logic.table_module;

import basic.money.Money;
import domain_logic.transaction_script.MfDate;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Tester {
  public static void main(String[] args) {
    try {
      // table gateway -> ResultSet -> RecordSet(Map) -> Table Module(Table(Map<Long, Map>))
      ProductGateway productGateway = new ProductGateway();
      List<Map> productList = TableGateway.convert(productGateway.findAll());
      Product.getInstance().setAll(productList);

      ContractGateway contractGateway = new ContractGateway();
      List<Map> list = TableGateway.convert(contractGateway.findRow(Long.valueOf(1)));
      for (Map<String, String> resultSet : list) {
        Long contractID = Long.valueOf(resultSet.get("ID"));
        Long productID = Long.valueOf(resultSet.get("product"));
        Money amount = Money.dollars(BigDecimal.valueOf(Long.valueOf(resultSet.get("revenue"))));
        MfDate mfDate = new MfDate(Date.valueOf(resultSet.get("dateSigned")));

        System.out.println(contractID + ", " + productID + ", " + amount.amount() + ", " + mfDate.toSqlDate());
        Contract.getInstance().set(resultSet);
      }

      Contract.getInstance().calculateRecognitions(Long.valueOf(1));
      System.out.println(RevenueRecognition.getInstance().table.toString());

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
