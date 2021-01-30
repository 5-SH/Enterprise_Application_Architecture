package data_source.data_mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonMapper extends AbstractMapper {
  public static final String COLUMNS = " id, lastname, firstname, numberOfDependents ";

  protected String findStatement() {
    return "SELECT " + COLUMNS + " FROM people " + "WHERE id =?";
  }

  public Person find(long id) throws SQLException {
    return find(new Long(id));
  }

  public Person find(Long id) throws SQLException {
    return (Person) abstractFind(id);
  }

  protected String findLastNameStatement =
    "SELECT " + COLUMNS +
      "FROM people " +
      "WHERE UPPER(lastname) like UPPER(?) " +
      "OREDER BY lastname";

  public List findByLastName(String name) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(findLastNameStatement);
    stmt.setString(1, name);
    ResultSet rs = stmt.executeQuery();
    return loadAll(rs);
  }

  protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
    String lastname = rs.getString(2);
    String firstname = rs.getString(3);
    int numberOfDependents = rs.getInt(4);
    return new Person(id, lastname, firstname, numberOfDependents);
  }

  // 공용 메서드를 제공해 반복적인 코드 작성 줄이기
  static class FindByLastname implements StatementSource {
    private String lastname;

    public FindByLastname(String lastname) {
      this.lastname = lastname;
    }

    @Override
    public String sql() {
      return "SELECT " + COLUMNS +
        " FROM people" +
        " WHERE UPPER(lastname) like UPPER(?)" +
        " ORDER BY lastname";
    }

    @Override
    public Object[] parameters() {
      Object[] result = {lastname};
      return result;
    }
  }

  public List findByLastName2(String pattern) throws SQLException {
    return findMany(new FindByLastname(pattern));
  }

  private static final String updateStatement = "UPDATE people " +
    " SET lastname = ?, firstname = ?, numberOfDependents = ?" +
    " WHERE id = ?";

  public void update(Person subject) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(updateStatement);
    stmt.setString(1, subject.getLastname());
    stmt.setString(2, subject.getFirstname());
    stmt.setInt(3, subject.getNumberOfDependents());
    stmt.setLong(4, subject.getId());
    stmt.executeUpdate();
  }

  @Override
  protected Long findNextDatabaseId() throws SQLException {
    PreparedStatement stmt = db.prepareStatement(findNextDatabaseIdStatment);
    ResultSet rs = stmt.executeQuery();
    rs.next();
    Long id = rs.getLong(1);
    return id;
  }

  protected String findNextDatabaseIdStatment = "SELECT COALESCE(MAX(id), 0) + 1 FROM people";


  @Override
  protected String insertStatement() {
    return "INSERT INTO people VALUES (?, ?, ?, ?)";
  }

  @Override
  protected void doInsert(DomainObject abstractSubject, PreparedStatement stmt) throws SQLException {
    Person subject = (Person) abstractSubject;
    stmt.setString(2, subject.getLastname());
    stmt.setString(3, subject.getFirstname());
    stmt.setInt(4, subject.getNumberOfDependents());
  }

  // 비어있는 객체로 작업
  @Override
  protected DomainObjectEL createDomainObject() {
    return new PersonEL();
  }

  @Override
  protected void doLoad(DomainObjectEL obj, ResultSet rs) throws SQLException {
    PersonEL person = (PersonEL) obj;
    person.dbLoadLastname(rs.getString(2));
    person.setFirstname(rs.getString(3));
    person.setNumberOfDependents(rs.getInt(4));
  }
}

