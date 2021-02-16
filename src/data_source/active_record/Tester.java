package data_source.active_record;

public class Tester {
  public static void main(String[] args) {
    Person person1 = Person.find(1);
    System.out.println(person1.getExemption().amount());

    Person person2 = Person.find(2);
    System.out.println(person2.getExemption().amount());

    Person person3 = new Person(new Long(3), "박", "건우", 3);
    person3.insert();
    System.out.println(person3.getExemption().amount());
  }
}
