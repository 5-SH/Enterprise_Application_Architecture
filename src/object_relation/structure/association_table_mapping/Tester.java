package object_relation.structure.association_table_mapping;

public class Tester {
  public static void main(String[] args) {
    Employee emp = MapperRegistry.employee().find(1);
    System.out.println(emp.toString());
  }
}
