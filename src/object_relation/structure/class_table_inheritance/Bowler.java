package object_relation.structure.class_table_inheritance;

public class Bowler extends Player {
  String bowlingAverage;

  public String getBowlingAverage() {
    return bowlingAverage;
  }

  public void setBowlingAverage(String bowlingAverage) {
    this.bowlingAverage = bowlingAverage;
  }

  @Override
  public String toString() {
    return "Bowler{" +
      "bowlingAverage='" + bowlingAverage + '\'' + ", name='" + super.getName() + '\'' + ", id='" + super.getId() + '\'' +
      '}';
  }
}
