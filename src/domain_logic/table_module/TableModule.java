package domain_logic.table_module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TableModule {
  protected Map table;

  public TableModule() {
    this.table = new HashMap<Long, HashMap>();
  }

  protected void set(Map recordSet) {
    doSet(recordSet);
  }

  protected void setAll(List<Map> list) {
    for (Map recordSet : list) {
      doSet(recordSet);
    }
  }

  protected Map get(Long id) {
    return (Map) table.get(id);
  }

  protected abstract Map getTable();

  protected abstract void doSet(Map recordSet);
}
