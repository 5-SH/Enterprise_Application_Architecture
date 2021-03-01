package object_relation.behavior.lazy_load.ghost;

import java.util.List;

public class Employee extends DomainObject {
  private String name;
  private Department department;
  private List<TimeRecord> timeRecords;

  public Employee(Long key) {
    super(key);
  }

  protected void load() {
    if (isGhost()) DataSource.load(this);
  }

  public String getName() {
    load();
    return name;
  }

  public void setName(String name) {
    load();
    this.name = name;
  }

  public Department getDepartment() {
    load();
    return department;
  }

  public void setDepartment(Department department) {
    load();
    this.department = department;
  }

  public List<TimeRecord> getTimeRecords() {
    load();
    return timeRecords;
  }

  public void setTimeRecords(List timeRecords) {
    load();
    this.timeRecords = timeRecords;
  }
}
