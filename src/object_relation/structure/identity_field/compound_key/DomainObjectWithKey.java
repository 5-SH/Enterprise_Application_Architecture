package object_relation.structure.identity_field.compound_key;

public class DomainObjectWithKey {
  private Key key;

  protected DomainObjectWithKey(Key key) {
    this.key = key;
  }

  protected DomainObjectWithKey() {}

  public Key getKey() {
    return key;
  }

  public void setKey(Key key) {
    this.key = key;
  }
}
