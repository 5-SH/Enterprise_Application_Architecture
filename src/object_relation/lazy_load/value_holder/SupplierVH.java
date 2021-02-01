package object_relation.lazy_load.value_holder;

import data_source.data_mapper.DomainObject;

import java.util.List;

public class SupplierVH extends DomainObject {
  private String name;
  private ValueHolder products;

  public SupplierVH(Long id, String name) {
    super(id);
    this.name = name;
  }

  public List getProducts() {
    return (List) products.getValue();
  }

  public void setProducts(ValueHolder products) {
    this.products = products;
  }
}
