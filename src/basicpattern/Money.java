package basicpattern;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

public class Money {
  private long amount;
  private Currency currency;

  public Money () {
    this.currency = Currency.getInstance(Locale.US);
    this.amount = 0;
  }

  public Money(double amount, Currency currency) {
    this.currency = currency;
    this.amount = Math.round(amount * centFactor());
  }

  public Money(long amount, Currency currency) {
    this.currency = currency;
    this.amount = amount * centFactor();
  }

  public Money(BigDecimal amount, Currency currency) {
    this.amount = amount.longValue() * centFactor();
    this.currency = currency;
  }

  private static final int[] cents = new int[] {1, 10, 100, 1000};

  private int centFactor() {
    return cents[currency.getDefaultFractionDigits()];
  }

  public BigDecimal amount() {
    return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
  }

  public Currency currency() {
    return currency;
  }

  public static Money dollars(double amount) {
    return new Money(amount, Currency.getInstance(Locale.US));
  }

  public boolean equals(Object other) {
    return (other instanceof Money) && equals((Money) other);
  }

  public boolean equals(Money other) {
    return currency.equals(other.currency) && (amount == other.amount);
  }

  public int hashCode() {
    return (int) (amount ^ (amount >>> 32));
  }

  public Money add(Money other) {
    assertSameCurrencyAs(other);
    return newMoney(amount + other.amount);
  }

  public Money subtract(Money other) {
    assertSameCurrencyAs(other);
    return newMoney(amount - other.amount);
  }

  private void assertSameCurrencyAs(Money arg) {
    assert (arg.currency == currency) : "money math mismatch";
  }

  private Money newMoney(long amount) {
    Money money = new Money();
    money.amount = amount;
    money.currency = this.currency;
    return money;
  }

  public int compareTo(Object other) {
    return compareTo((Money)other);
  }

  public int compareTo(Money other) {
    assertSameCurrencyAs(other);
    if (amount < other.amount) return -1;
    else if (amount == other.amount) return 0;
    else return 1;
  }

  public boolean greaterThan(Money other) {
    return (compareTo(other) > 0);
  }

  public Money multiply(double amount) {
    return multiply(new BigDecimal(amount));
  }

  public Money multiply(BigDecimal amount) {
    return multiply(amount, RoundingMode.HALF_EVEN);
  }

  public Money multiply(BigDecimal amount, RoundingMode roundingMode) {
    return new Money(amount().multiply(amount, new MathContext(16, roundingMode)), currency);
  }

  public Money[] allocate(int n) {
    Money lowResult = newMoney(amount / n);
    Money highResult = newMoney(lowResult.amount + 1);
    Money[] results = new Money[n];
    int remainder = (int) amount % n;
    for (int i = 0; i < remainder; i++) results[i] = highResult;
    for (int i = remainder; i < n; i++) results[i] = lowResult;
    return results;
  }

  public Money[] allocate(long[] ratios) {
    long total = 0;
    for (int i = 0; i < ratios.length; i++) total += ratios[i];
    long remainder = amount;
    Money[] results = new Money[ratios.length];
    for (int i = 0; i < results.length; i++) {
      results[i] = newMoney(amount * ratios[i] / total);
      remainder -= results[i].amount;
    }
    for (int i = 0; i < remainder; i++) {
      results[i].amount++;
    }
    return results;
  }
}
