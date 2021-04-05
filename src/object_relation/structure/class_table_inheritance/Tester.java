package object_relation.structure.class_table_inheritance;

public class Tester {
  public static void main(String[] args) {
    PlayerMapper playerMapper = new PlayerMapper();
    Player p1 = playerMapper.find(new Long(1));

    switch (p1.getType()) {
      case CricketerMapper.TYPE_CODE:
        p1 = (Cricketer) p1;
      case BowlerMapper.TYPE_CODE:
      case FootballerMapper.TYPE_CODE:
    }

    System.out.println(p1);
  }
}
