package object_relation.structure.identity_field.compound_key;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
