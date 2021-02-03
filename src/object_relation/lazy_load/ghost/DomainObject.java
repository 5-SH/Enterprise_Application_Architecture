package object_relation.lazy_load.ghost;

public class DomainObject {
  enum LoadStatus { GHOST, LOADING, LOADED };

  LoadStatus status;
  Long key;

  public DomainObject(Long key) {
    status = LoadStatus.GHOST;
    this.key = key;
  }

  public Long getKey() {
    return key;
  }

  public Boolean isGhost() {
    return status == LoadStatus.GHOST;
  }

  public Boolean isLoaded() {
    return status == LoadStatus.LOADED;
  }

  public void markLoading() {
    status = LoadStatus.LOADING;
  }

  public void markLoaded() {
    status = LoadStatus.LOADED;
  }
}
