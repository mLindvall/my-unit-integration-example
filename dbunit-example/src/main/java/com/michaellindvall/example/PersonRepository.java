/**
 * 
 */
package com.michaellindvall.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import com.michaellindvall.example.model.Person;

/**
 * @author mlindvall
 * 
 */
public class PersonRepository {

  private final DataSource dataSource;

  public PersonRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Person findPersonByFirstName(String name) throws SQLException {
    Person person = null;
    PreparedStatement statement = dataSource.getConnection().prepareStatement("select * from PERSON where NAME = ?");
    statement.setString(1, name);
    ResultSet resultSet = null;
    try {
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        person = convertSingleRow(resultSet);
      }
    } finally {
      closeQuietly(resultSet);
      closeQuietly(statement);
    }
    return person;
  }

  private Person convertSingleRow(ResultSet resultSet) throws SQLException {
    String firstName = resultSet.getString("NAME");
    String lastName = resultSet.getString("LAST_NAME");
    int age = resultSet.getInt("AGE");
    return new Person(firstName, lastName, age);
  }

  private void closeQuietly(ResultSet resultSet) {
    try {
      resultSet.close();
    } catch (SQLException exception) {
    }
  }

  private void closeQuietly(Statement statement) {
    try {
      statement.close();
    } catch (SQLException exception) {
    }
  }

}
