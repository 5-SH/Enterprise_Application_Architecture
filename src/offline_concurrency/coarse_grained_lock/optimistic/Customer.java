package offline_concurrency.coarse_grained_lock.optimistic;

import basic.plugin.IdGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Customer extends DomainObject {
  private String name;
  private List addressList;

  public Customer(Long id, Version version, String name) {
    super(id);
    setSystemFields(version, new Timestamp(System.currentTimeMillis()), "admin");
    this.name = name;
  }

  private Customer(Long id, Version version, String name, List addressList) {
    super(id);
    setSystemFields(version, new Timestamp(System.currentTimeMillis()), "admin");
    this.name = name;
    this.addressList = addressList;
  }

  public static Customer create(String name) {
    return new Customer(IdGenerator.INSTANCE.nextId(), Version.create(), name, new ArrayList<>());
  }

  public Address addAddress(String line1, String city, String state) {
    Address address = Address.create(this, getVersion(), line1, city, state);
    addressList.add(address);
    return address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List getAddressList() {
    return addressList;
  }

  public void setAddressList(List addressList) {
    this.addressList = addressList;
  }

  @Override
  public String toString() {
    return "Customer{" +
      "name='" + name + '\'' +
      ", addressList=" + addressList +
      '}';
  }
}
