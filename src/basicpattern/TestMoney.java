package basicpattern;

public class TestMoney {
  public static void main(String[] args) {
    long[] allocation = { 3, 7 };
    Money[] result = Money.dollars(0.05).allocate(allocation);
    for(Money money : result) {
      System.out.println(money.amount());
    }
  }
}
