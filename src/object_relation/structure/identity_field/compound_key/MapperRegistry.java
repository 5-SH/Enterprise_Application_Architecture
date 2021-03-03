package object_relation.structure.identity_field.compound_key;

public class MapperRegistry {
  private static MapperRegistry instnace = new MapperRegistry();
  protected OrderMapper orderMapper = new OrderMapper();
  protected LineItemMapper lineItemMapper = new LineItemMapper();

  private static MapperRegistry getInstance() { return instnace; }

  public static OrderMapper order() {
    return getInstance().orderMapper;
  }

  public static LineItemMapper lineItem() {
    return getInstance().lineItemMapper;
  }
}
