package object_relation.structure.association_table_mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AssociationTableLoader {
  public static interface Adder {
    void add(DomainObject host, ResultSet rs) throws SQLException;
  }

  private AbstractMapper sourceMapper;
  private Adder targetAdder;
  private ResultSet rs = null;
  private List resultIds = new ArrayList();
  private Map inProgress = new HashMap();

  public AssociationTableLoader(AbstractMapper sourceMapper, Adder targetAdder) {
    this.sourceMapper = sourceMapper;
    this.targetAdder = targetAdder;
  }

  protected List run(String sql) {
    loadData(sql);
    addAllNewObjectsToIdentityMap();
    return formResult();
  }

  private void loadData(String sql) {
    PreparedStatement stmt = null;
    try {
      stmt = MapperRegistry.DB().prepareStatement(sql);
      rs = stmt.executeQuery();
      while (rs.next()) {
        loadRow();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void loadRow() throws SQLException {
    Long ID = new Long(rs.getLong(1));
    if (!resultIds.contains(ID)) resultIds.add(ID);
    if (!sourceMapper.hasLoaded(ID)) {
      if (!inProgress.keySet().contains(ID)) {
        inProgress.put(ID, sourceMapper.loadRow(ID, rs));
      }
      targetAdder.add((DomainObject) inProgress.get(ID), rs);
    }
  }

  private void addAllNewObjectsToIdentityMap() {
    for (Iterator it = inProgress.values().iterator(); it.hasNext();) {
      sourceMapper.putAsLoaded((DomainObject) it.next());
    }
  }

  private List formResult() {
    List result = new ArrayList();
    for (Iterator it = resultIds.iterator(); it.hasNext();) {
      Long id = (Long) it.next();
      result.add(sourceMapper.lookUp(id));
    }
    return result;
  }

}
