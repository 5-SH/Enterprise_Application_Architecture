package object_relation.structure.embeded_value;

public class DomainObject {
  private Long id;

  public DomainObject(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
