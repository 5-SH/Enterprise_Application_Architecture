package data_source.data_mapper;

public interface StatementSource {
  String sql();
  Object[] parameters();
}
