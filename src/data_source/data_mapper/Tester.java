package data_source.data_mapper;

import java.sql.SQLException;

public class Tester {
  public static void main(String[] args) throws SQLException {
    PersonMapper personMapper = new PersonMapper();
    Person newPerson = new Person("오", "승호", 1);
    personMapper.insert(newPerson);

    Person foundPerson = personMapper.find(1);
    System.out.println(foundPerson.getLastname() + " " +
      foundPerson.getFirstname() +
      " / number of dependents : " +
      foundPerson.getNumberOfDependents());

    foundPerson.setLastname("주");
    foundPerson.setFirstname("애린");
    personMapper.update(foundPerson);

    foundPerson = personMapper.find(1);
    System.out.println(foundPerson.getLastname() + " " +
      foundPerson.getFirstname() +
      " / number of dependents : " +
      foundPerson.getNumberOfDependents());
  }
}
