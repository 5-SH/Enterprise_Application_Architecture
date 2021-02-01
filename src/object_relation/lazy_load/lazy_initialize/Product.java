package object_relation.lazy_load.lazy_initialize;

import data_source.data_mapper.DomainObject;
import domain_logic.domain_model.CompleteRecognitionStrategy;
import domain_logic.domain_model.Contract;
import domain_logic.domain_model.RecognitionStrategy;
import domain_logic.domain_model.ThreeWayRecognitionStrategy;

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
