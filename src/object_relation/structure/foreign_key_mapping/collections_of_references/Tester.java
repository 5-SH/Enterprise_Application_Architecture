package object_relation.structure.foreign_key_mapping.collections_of_references;

public class Tester {
  public static void main(String[] args) {
    Team team = MapperRegistry.team().find(1);
    System.out.println(team);

    // update
    Team team1 = MapperRegistry.team().find(1);
    Player updatePlayer = team1.getPlayer(new Long(1));
    team1.removePlayer(updatePlayer);
    MapperRegistry.team().update(team1);
    System.out.println(team1);

    Team team2 = MapperRegistry.team().find(2);
    team2.addPlayer(updatePlayer);
    System.out.println(team2);
  }
}
