package object_relation.structure.foreign_key_mapping.collections_of_references;

public class Tester {
  public static void main(String[] args) {
    Team team = MapperRegistry.team().find(1);
    System.out.println(team);
  }
}
