package object_relation.lazy_load.ghost;

public class Department extends DomainObject {
  String name;

  public Department(Long key) {
    super(key);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
