package offline_concurrency.coarse_grained_lock;

import basic.plugin.IdGenerator;

import java.sql.Timestamp;

public class Address extends DomainObject {
  private String line1;
  private String city;
  private String state;
  private Customer customer;

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
}
