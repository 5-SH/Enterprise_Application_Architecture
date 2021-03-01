package object_relation.behavior.lazy_load.lazy_initialize;

import data_source.data_mapper.DomainObject;

public class Product extends DomainObject {
  private String name;
  private String type;

  public Product(long id, String name, String type) {
    super(id);
    this.name = name;
    this.type = type;
  }

  public Long getId() {
    return super.getId();
  }

  @Override
  public String toString() {
    return "Product{" +
      "id=" + super.getId() +
      ", name='" + name + '\'' +
      ", type='" + type + '\'' +
      '}';
  }
}
