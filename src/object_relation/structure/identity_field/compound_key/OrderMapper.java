package object_relation.structure.identity_field.compound_key;

public class OrderMapper extends AbstractMapper {
  public Order find(Key key) {
    return (Order) abstractFind(key);
  }

  public Order find(Long id) {
    return find(new Key(id));
  }

  @Override
  protected String findStatementString() {
    return "SELECT id, customer from orders WHERE id = ?";
  }
}
