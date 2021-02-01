package object_relation.lazy_load.lazy_initialize;

import data_source.data_mapper.DomainObject;

import java.sql.SQLException;
import java.util.List;

public class Supplier extends DomainObject {
  private String name;
  private List products;
  private ProductGateway productGateway;

  public Supplier(long id, String name) {
    super(id);
    this.name = name;
    this.products = null;
    productGateway = new ProductGateway();
  }

  public List getProducts() throws SQLException {
    if (products == null) products = productGateway.findForSupplier(getId());
    return products;
  }

  public Long getId() {
    return super.getId();
  }
}
