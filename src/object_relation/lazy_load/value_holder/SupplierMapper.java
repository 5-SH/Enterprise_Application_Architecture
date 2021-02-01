package object_relation.lazy_load.value_holder;

import data_source.data_mapper.DomainObject;
import object_relation.lazy_load.virtual_proxy.VirtualList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierMapper {
  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    Long idArg = rs.getLong(1);
    String nameArg = rs.getString(2);
    SupplierVH result = new SupplierVH(id, nameArg);
    result.setProducts(new ValueHolder(new ProductLoader(idArg)));
    return result;
  }
}
