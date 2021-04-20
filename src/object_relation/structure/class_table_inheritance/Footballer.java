package object_relation.structure.class_table_inheritance;

public class Footballer extends Player {
  String club;

  public Footballer() {
  }

  public Footballer(long id, String name, String type, String club) {
    this.setId(id);
    this.setName(name);
    this.setType(type);
    this.club = club;
  }

  public String getClub() {
    return club;
  }

  public void setClub(String club) {
    this.club = club;
  }

  @Override
  public String toString() {
    return "Footballer{" +
      "club='" + club + '\'' + ", name='" + super.getName() + '\'' + ", id='" + super.getId() + '\'' +
      '}';
  }
}
