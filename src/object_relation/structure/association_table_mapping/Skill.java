package object_relation.structure.association_table_mapping;

public class Skill extends DomainObject {
  private String name;

  public Skill(Long id, String name) {
    super(id);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Skill{" +
      "name='" + name + '\'' +
      '}';
  }
}
