package object_relation.structure.embeded_value;

import basic.money.Money;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Locale;

public class ProductOffering {
  private Integer ID;
  private Product product;
  private Money baseCost;

  public ProductOffering(Integer ID, Product product, Money baseCost) {
    this.ID = ID;
    this.product = product;
    this.baseCost = baseCost;
  }

  private final static String findStatementString = "SELECT product, base_cost_amount, base_cost_currency FROM productOffering WHERE product = ?";

  public static ProductOffering find(Long id) {
    ProductOffering result = null;
    try {
      PreparedStatement stmt = Registry.DB().prepareStatement(findStatementString);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = load(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  public static ProductOffering find(long id) { return find(new Long(id)); }

  public static ProductOffering load(ResultSet rs) {
    Integer productID = null;
    Product product = null;
    Money baseCost = null;
    try {
      BigDecimal baseCostAmount = rs.getBigDecimal("base_cost_amount");
      Currency baseCostCurrency = getCurrency(rs.getString("base_cost_currency"));
      baseCost = new Money(baseCostAmount, baseCostCurrency);
      productID = (Integer) rs.getObject("product");
      product = Product.find(productID);

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return new ProductOffering(productID, product, baseCost);
  }

  private static Currency getCurrency(String currency) {
    Currency result = null;
    switch (currency) {
      case "USD":
        result = Currency.getInstance(Locale.US);
      default:
        result = Currency.getInstance(Locale.US);
    }

    return result;
  }

  private final static String updateStatementString = "UPDATE productOffering SET base_cost_amount = ?, base_cost_currency = ? WHERE product = ?";

  public void update() {
    PreparedStatement stmt = null;
    try {
      stmt = Registry.DB().prepareStatement(updateStatementString);
      stmt.setBigDecimal(1, baseCost.amount());
      stmt.setString(2, baseCost.currency().getCurrencyCode());
      stmt.setInt(3, ID.intValue());
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Money getBaseCost() {
    return baseCost;
  }

  public void setBaseCost(Money baseCost) {
    this.baseCost = baseCost;
  }

  @Override
  public String toString() {
    return "ProductOffering{" +
      "ID=" + ID +
      ", product=" + product +
      ", baseCost=" + baseCost.amount() +
      '}';
  }
}
