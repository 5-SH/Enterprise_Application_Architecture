package data_source.row_data_gateway;

import java.util.Iterator;

public class Tester {
  public static void main(String[] args) {
    PersonFinder finder = new PersonFinder();
    Iterator people = finder.findResponsibles().iterator();
    StringBuffer result = new StringBuffer();

    while (people.hasNext()) {
      PersonGateway each = (PersonGateway) people.next();
      result.append(each.getLastName());
      result.append("");
      result.append(each.getFirstName());
      result.append("");
      result.append(String.valueOf(each.getNumberOfDependents()));
      result.append("\n");
    }

    System.out.println(result);
  }
}
