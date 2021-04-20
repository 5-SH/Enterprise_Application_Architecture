package object_relation.structure.concrete_table_inheritance;

public class Cricketer extends Player {
  String battingAverage;

  public String getBattingAverage() {
    return battingAverage;
  }

  public void setBattingAverage(String battingAverage) {
    this.battingAverage = battingAverage;
  }

  @Override
  public String toString() {
    return "Cricketer{" +
      "battingAverage='" + battingAverage + '\'' + ", name='" + super.getName() + '\'' + ", id='" + super.getId() + '\'' +
      '}';
  }
}
