package object_relation.lazy_load.virtual_proxy;

import data_source.data_mapper.DomainObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierMapper {

  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    Long idArg = rs.getLong(1);
    String nameArg = rs.getString(2);
    SupplierVL result = new SupplierVL(id, nameArg);
    result.setProducts(new VirtualList(new ProductLoader(idArg)));
    return result;
  }
}
