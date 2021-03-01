package object_relation.behavior.lazy_load.ghost;

public class Department extends DomainObject {
  String name;

  public Department(Long key) {
    super(key);
  }

  protected void load() {
    if (isGhost()) DataSource.load(this);
  }

  public String getName() {
    load();
    return name;
  }

  public void setName(String name) {
    load();
    this.name = name;
  }
}
