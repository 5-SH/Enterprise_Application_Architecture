package object_relation.structure.identity_field.compound_key;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class LineItemMapper extends AbstractMapper {
  public LineItem find(long orderID, long seq) {
    Key key = new Key(new Long(orderID), new Long(seq));
    return (LineItem) abstractFind(key);
  }

  public LineItem find(Key key) {
    return (LineItem) abstractFind(key);
  }

  @Override
  protected String findStatementString() {
    return "SELECT orderID, seq, amount, product " +
            "FROM line_items " +
            "WHERE (orderID = ?) AND (seq = ?)";
  }

  protected void loadFindStatement(Key key, PreparedStatement finder) throws SQLException {
    finder.setLong(1, orderID(key));
    finder.setLong(2, sequenceNumber(key));
  }

  private static long orderID(Key key) {
    return key.longValue(0);
  }

  private static long sequenceNumber(Key key) {
    return key.longValue(1);
  }

  @Override
  protected DomainObjectWithKey doLoad(Key key, ResultSet rs) throws SQLException {
    Order theOrder = MapperRegistry.order().find(orderID(key));
    return doLoad(key, rs, theOrder);
  }

  protected DomainObjectWithKey doLoad(Key key, ResultSet rs, Order order) throws SQLException {
    LineItem result;
    int amount = rs.getInt("amount");
    String product = rs.getString("product");
    result = new LineItem(key, amount, product);
    order.addLineItem(result);
    return result;
  }

  protected Key createKey(ResultSet rs) throws SQLException {
    Key key = new Key(new Long(rs.getLong("orderID")), new Long(rs.getLong("seq")));
    return key;
  }

  public void loadAllLineItemsFor(Order arg) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = DB.prepareStatement(findForOrderString);
      stmt.setLong(1, arg.getKey().longValue());
      rs = stmt.executeQuery();
      while (rs.next()) {
        load(rs, arg);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private final static String findForOrderString = "SELECT orderID, seq, amount, product " +
                                                    "FROM line_items " +
                                                    "WHERE orderID = ?";

  protected DomainObjectWithKey load(ResultSet rs, Order order) throws SQLException {
    Key key = createKey(rs);
    if (loadedMap.containsKey(key)) return (DomainObjectWithKey) loadedMap.get(key);
    DomainObjectWithKey result = doLoad(key, rs, order);
    loadedMap.put(key, result);
    return result;
  }

  public Key insert(DomainObjectWithKey subject) {
    throw new UnsupportedOperationException("Must supply an order when inserting a line item");
  }

  public Key insert(LineItem item, Order order) {
    Key key = null;
    try {
      key = new Key(order.getKey().value(), getNextSequenceNumber(order));
      key = performInsert(item, key);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    item.setKey(key);
    order.addLineItem(item);
    return key;
  }

  private Long getNextSequenceNumber(Order order) {
    loadAllLineItemsFor(order);
    Iterator it = order.getLineItemList().iterator();
    if (it.hasNext()) {
      LineItem candidate = (LineItem) it.next();

      while (it.hasNext()) {
        LineItem thisItem = (LineItem) it.next();
        if (thisItem.getKey() == null) continue;
        if (sequenceNumber(thisItem) > sequenceNumber(candidate)) candidate = thisItem;
      }
      return Long.valueOf(sequenceNumber(candidate) + 1);
    } else {
      return Long.valueOf(1);
    }
  }

  private static long sequenceNumber(LineItem li) {
    return sequenceNumber(li.getKey());
  }

  @Override
  protected Key findNextDatabaseKeyObject() throws SQLException {
    throw new UnsupportedOperationException("Must supply an order when inserting a line item");
  }

  @Override
  protected String insertStatementString() {
    return "INSERT INTO line_items VALUES (?, ?, ?, ?)";
  }

  @Override
  protected void insertData(DomainObjectWithKey subject, PreparedStatement stmt) throws SQLException {
    LineItem item = (LineItem) subject;
    stmt.setInt(3, item.getAmount());
    stmt.setString(4, item.getProduct());
  }

  protected void insertKey(DomainObjectWithKey subject, PreparedStatement stmt) throws SQLException {
    stmt.setLong(1, orderID(subject.getKey()));
    stmt.setLong(2, sequenceNumber(subject.getKey()));
  }
}
