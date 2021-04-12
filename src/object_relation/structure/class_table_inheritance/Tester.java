package object_relation.structure.class_table_inheritance;

public class Tester {
  public static void main(String[] args) {
    PlayerMapper playerMapper = new PlayerMapper();
    Player p1 = playerMapper.find(new Long(1));

    switch (p1.getType()) {
      case CricketerMapper.TYPE_CODE:
        p1 = (Cricketer) p1;
        break;
      case BowlerMapper.TYPE_CODE:
        p1 = (Bowler) p1;
        break;
      case FootballerMapper.TYPE_CODE:
        p1 = (Footballer) p1;
        break;
    }
    System.out.println(p1);

    Cricketer c1 = (Cricketer) playerMapper.find(1);
    c1.setBattingAverage("3.01");
    playerMapper.update(c1);
    System.out.println(c1);

    Bowler b1 = new Bowler(4, "michael", "150");
    playerMapper.insert(b1);
//    playerMapper.delete(b1);

  }
}
