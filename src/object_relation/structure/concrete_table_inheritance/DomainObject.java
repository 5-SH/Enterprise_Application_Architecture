package object_relation.structure.concrete_table_inheritance;

public class DomainObject {
  private Long id;

  public DomainObject() {}

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
