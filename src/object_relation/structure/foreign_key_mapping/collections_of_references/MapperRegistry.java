package object_relation.structure.foreign_key_mapping.collections_of_references;

public class MapperRegistry {
  private static MapperRegistry instance = new MapperRegistry();
  protected TeamMapper teamMapper = new TeamMapper();
  protected PlayerMapper playerMapper = new PlayerMapper();

  private static MapperRegistry getInstance() { return instance; }

  public static TeamMapper team() { return getInstance().teamMapper; }

  public static PlayerMapper player() { return getInstance().playerMapper; }
}
