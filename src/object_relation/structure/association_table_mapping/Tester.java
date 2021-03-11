package object_relation.structure.association_table_mapping;

public class Tester {
  public static void main(String[] args) {
    Employee emp = MapperRegistry.employee().find(1);
    System.out.println(emp.toString());

    // update
    Employee emp1 = MapperRegistry.employee().find(1);
    System.out.println(emp1.toString());
    Skill updateSkill = emp1.getSkill(1);
    emp1.removeSkill(updateSkill);
    MapperRegistry.employee().update(emp1);
    System.out.println(emp1.toString());

    Employee emp2 = MapperRegistry.employee().find(2);
    System.out.println(emp2.toString());
    emp2.addSkill(updateSkill);
    MapperRegistry.employee().update(emp2);
    System.out.println(emp2.toString());
  }
}