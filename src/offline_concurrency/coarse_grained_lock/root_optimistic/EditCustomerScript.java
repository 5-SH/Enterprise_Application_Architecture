package offline_concurrency.coarse_grained_lock.root_optimistic;

import java.util.List;

public class EditCustomerScript {
  public static Customer createCustomerByNameAndAddressList(String name, List<String[]> addressList) {
    UnitOfWork.newCurrent();
    Customer newCustomer = Customer.create(name);
    for (String[] list : addressList) {
      newCustomer.addAddress(list[0], list[1], list[2]);
    }
    newCustomer.markNew();
    UnitOfWork.getCurrent().commit();

    return newCustomer;
  }

  public static void updateCustomerName(Customer customer, String name) {
    UnitOfWork.newCurrent();
    customer.setName(name);
    customer.markDirty();
    UnitOfWork.getCurrent().commit();
  }
}
