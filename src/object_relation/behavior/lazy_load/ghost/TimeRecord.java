package object_relation.behavior.lazy_load.ghost;

public class TimeRecord extends DomainObject {
  private Long employee_id;
  private String startTime;
  private String endTime;

  public TimeRecord(Long key) {
    super(key);
  }

  protected void load() {
    if (isGhost()) DataSource.load(this);
  }

  public String getStartTime() {
    load();
    return startTime;
  }

  public void setStartTime(String startTime) {
    load();
    this.startTime = startTime;
  }

  public String getEndTime() {
    load();
    return endTime;
  }

  public void setEndTime(String endTime) {
    load();
    this.endTime = endTime;
  }

  public Long getEmployee_id() {
    load();
    return employee_id;
  }

  public void setEmployee_id(Long employee_id) {
    load();
    this.employee_id = employee_id;
  }
}
