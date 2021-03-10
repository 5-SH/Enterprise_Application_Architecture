package data_source.table_data_gateway;

import java.sql.ResultSet;

public class Tester {
  public static void main(String[] args) throws Exception {
    PersonGateway personGateway = new PersonGateway();
    personGateway.Insert("오", "승호", 1);
    ResultSet result = personGateway.FindRow(1);

    while (result.next()) {
      String firstname = result.getString(2);
      String lastname = result.getString(3);
      Long numberOfDependents = result.getLong(4);
      System.out.println(lastname + " " +
        firstname +
        " / number of dependents : " +
        numberOfDependents);
    }
  }
}
