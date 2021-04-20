package object_relation.structure.single_table_inheritance;

public class Footballer extends Player {
  String club;

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
