package offline_concurrency.coarse_grained_lock.root_optimistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tester {
  public static void main(String[] args) {
    List addressList = new ArrayList();
    String[] addr1 = new String[3];
    addr1[0] = "행운동";
    addr1[1] = "관악구";
    addr1[2] = "서울시";
    addressList.add(addr1);

    String[] addr2 = new String[3];
    addr2[0] = "좌동";
    addr2[1] = "해운대구";
    addr2[2] = "부산시";
    addressList.add(addr2);

    Customer newCustomer = EditCustomerScript.createCustomerByNameAndAddressList("승호", addressList);
    EditCustomerScript.updateCustomerName(newCustomer, "수정된 승호의 이름");


  }
}
