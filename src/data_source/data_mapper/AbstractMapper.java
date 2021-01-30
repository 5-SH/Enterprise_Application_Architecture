package data_source.data_mapper;

import java.sql.*;
import java.util.*;

public abstract class AbstractMapper {
  protected Map loadedMap = new HashMap<>();
  protected Connection db;
  abstract protected String findStatement();

  public AbstractMapper() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties props = new Properties();
      props.put("user", "root");
      props.put("password", "root");
      props.put("characterEncoding", "UTF-8");
      String url = "jdbc:mysql://localhost/architecture";
      this.db = DriverManager.getConnection(url, props);
    } catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }
  }

  protected DomainObject abstractFind(Long id) throws SQLException {
    DomainObject result = (DomainObject) loadedMap.get(id);
    if (result != null) return result;
    PreparedStatement findStatement = db.prepareStatement(findStatement());
    findStatement.setLong(1, id.longValue());
    ResultSet rs = findStatement.executeQuery();
    rs.next();
    result = load(rs);
    return result;
  }

  protected DomainObject load(ResultSet rs) throws SQLException {
    Long id = new Long(rs.getLong(1));
    if (loadedMap.containsKey(id)) return (DomainObject) loadedMap.get(id);
    DomainObject result = doLoad(id, rs);
    loadedMap.put(id, result);
    return result;
  }

  abstract protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException;

  protected List loadAll(ResultSet rs) throws SQLException {
    List result = new ArrayList();
    while(rs.next()) {
      result.add(load(rs));
    }
    return result;
  }

  // 공용 검색기 메서드를 제공해 반복적인 코드 작성 줄이기
  public List findMany(StatementSource source) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(source.sql());
    for (int i = 0; i < source.parameters().length; i++) {
      stmt.setObject(i + 1, source.parameters()[i]);
    }

    ResultSet rs = stmt.executeQuery();
    return loadAll(rs);
  }

  public Long insert(DomainObject subject) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(insertStatement());
    subject.setId(findNextDatabaseId());
    stmt.setInt(1, subject.getId().intValue());
    doInsert(subject, stmt);
    stmt.executeUpdate();
    loadedMap.put(subject.getId(), subject);
    return subject.getId();
  }

  abstract protected Long findNextDatabaseId() throws SQLException;
  abstract protected String insertStatement() throws SQLException;
  abstract protected void doInsert(DomainObject abstractSubject, PreparedStatement insertStatement) throws SQLException;

}
