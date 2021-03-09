package object_relation.structure.foreign_key_mapping;

public class Artist extends DomainObject {
  private String name;

  public Artist(Long id, String name) {
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
    return "Artist{" +
      "id='" + getId() + '\'' +
      "name='" + name + '\'' +
      '}';
  }
}
