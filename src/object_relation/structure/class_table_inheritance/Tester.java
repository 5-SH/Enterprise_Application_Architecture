package object_relation.structure.class_table_inheritance;

public class Tester {
  public static void main(String[] args) {
    PlayerMapper playerMapper = new PlayerMapper();
    Player p1 = playerMapper.find(3);
    if (p1 != null) {
      switch (p1.getType()) {
        case FootballerMapper.TYPE_CODE:
          p1 = (Footballer) p1;
          break;
        case BowlerMapper.TYPE_CODE:
          p1 = (Bowler) p1;
          break;
        case CricketerMapper.TYPE_CODE:
          p1 = (Cricketer) p1;
          break;
      }
    }

    System.out.println(p1);
  }
}
