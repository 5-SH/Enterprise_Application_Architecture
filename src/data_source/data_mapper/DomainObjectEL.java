package data_source.data_mapper;

public class DomainObjectEL {
  private Long id;
  private int state = LOADING;
  private static final int LOADING = 0;
  private static final int ACTIVE = 1;

  public DomainObjectEL() {}

  public DomainObjectEL(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void beActive() {
    state = ACTIVE;
  }

  protected void assertStateIsLoading() {
    assert (state == LOADING) : "lastname loaded";
  }
}
