package object_relation.behavior.unit_of_work;

public class AlbumFactory {
  private static class IdGenerator {
    static Long id = new Long(0);

    public static Long nextId() {
      return ++id;
    }
  }

  public static Album create(String title) {
    Album obj = new Album(IdGenerator.nextId(), title);
    obj.markNew();
    return obj;
  }
}
