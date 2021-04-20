package object_relation.structure.concrete_table_inheritance;

public class Cricketer extends Player {
  String battingAverage;

  public Cricketer() {
  }

  public Cricketer(String name, String type, String battingAverage) {
    this.setName(name);
    this.setType(type);
    this.battingAverage = battingAverage;
  }

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
