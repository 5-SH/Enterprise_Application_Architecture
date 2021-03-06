package offline_concurrency.coarse_grained_lock.pessimistic;

import basic.plugin.IdGenerator;

import java.sql.Timestamp;

public class Address extends DomainObject {
  private String line1;
  private String city;
  private String state;
  private Customer customer;

  public Address(Long id, String line1, String city, String state) {
    super(id);
    this.line1 = line1;
    this.city = city;
    this.state = state;
  }

  private Address(Long id, Version version, Customer customer, String line1, String city, String state) {
    super(id);
    setSystemFields(version, new Timestamp(System.currentTimeMillis()), "admin");
    this.line1 = line1;
    this.city = city;
    this.state = state;
    this.customer = customer;
  }

  public static Address create(Customer customer, Version version, String line1, String city, String state) {
    return new Address(IdGenerator.INSTANCE.nextId(), version, customer, line1, city, state);
  }

  public String getLine1() {
    return line1;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Address addr = (Address) obj;
    return getId() == addr.getId();
  }

  @Override
  public String toString() {
    return "Address{" +
      "line1='" + line1 + '\'' +
      ", city='" + city + '\'' +
      ", state='" + state +
      '}';
  }
}
