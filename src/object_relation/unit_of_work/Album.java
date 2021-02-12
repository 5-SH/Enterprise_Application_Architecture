package object_relation.unit_of_work;

public class Album extends DomainObject {
  private String title;

  public Album(Long id, String title) {
    super(id);
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
    markDirty();
  }
}
