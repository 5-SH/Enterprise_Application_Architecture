package object_relation.lazy_load.virtual_proxy;

import data_source.data_mapper.DomainObject;
import object_relation.lazy_load.lazy_initialize.ProductGateway;

import java.sql.SQLException;
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
