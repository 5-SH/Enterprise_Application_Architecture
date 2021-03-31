package object_relation.structure.serialized_lob;

import java.util.ArrayList;
import java.util.List;

public class Tester {
  public static void main(String[] args) throws Exception {
    Department Vermont = new Department("Vermont");
    Department Boston = new Department("Boston");
    List englandList = new ArrayList();
    englandList.add(Vermont);
    englandList.add(Boston);
    Department England = new Department("England", englandList);

    Department California = new Department("California");
    Department MidWest = new Department("Mid-West");

    List USList = new ArrayList();
    USList.add(England);
    USList.add(California);
    USList.add(MidWest);
    Department US = new Department("US", USList);

    Department Europe = new Department("Europe");

    List departments = new ArrayList();
    departments.add(US);
    departments.add(Europe);

    Customer customer = new Customer("james", departments);
//    System.out.println(customer.xmlStringer());
    customer.insert();
  }
}
