package object_relation.behavior.lazy_load.virtual_proxy;

import data_source.data_mapper.DomainObject;
import java.util.List;

// virtual proxy
public class SupplierVL extends DomainObject {
  private String name;
  private List products;

  public SupplierVL(Long id, String name) {
    super(id);
    this.name = name;
  }

  public void setProducts(List products) {
    this.products = products;
  }

  public List getProducts() {
    return products;
  }
}
