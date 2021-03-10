package object_relation.structure.foreign_key_mapping.single_valued_reference;

public class MapperRegistry {
  private static MapperRegistry instance = new MapperRegistry();
  protected AlbumMapper albumMapper = new AlbumMapper();
  protected ArtistMapper artistMapper = new ArtistMapper();

  private static MapperRegistry getInstance() { return instance; }

  public static AlbumMapper album() { return getInstance().albumMapper; }

  public static ArtistMapper artist() { return getInstance().artistMapper; }
}
