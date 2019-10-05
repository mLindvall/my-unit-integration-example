/**
 * 
 */
package com.michaellindvall.tests;

import static org.h2.engine.Constants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.io.File;
import javax.sql.DataSource;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.michaellindvall.example.PersonRepository;
import com.michaellindvall.example.model.Person;

/**
 * @author mlindvall
 * 
 */
public class XmlDatabaseTest {

  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void findsAndReadsExistingPersonByFirstName() throws Exception {
    PersonRepository repository = new PersonRepository(dataSource());
    Person charlie = repository.findPersonByFirstName("Charlie");

    assertThat(charlie.getFirstName(), is("Charlie"));
    assertThat(charlie.getLastName(), is("Brown"));
    assertThat(charlie.getAge(), is(42));
  }

  private IDataSet readDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File("dataset.xml"));
  }

  private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
  private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
  private static final String USER = "sa";
  private static final String PASSWORD = "";

  private DataSource dataSource() {
    JdbcDataSource dataSource = new JdbcDataSource();
    dataSource.setURL(JDBC_URL);
    dataSource.setUser(USER);
    dataSource.setPassword(PASSWORD);
    return dataSource;
  }
  
  private void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
    IDatabaseTester databaseTester = new JdbcDatabaseTester(
        "org.h2.Driver", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
    databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();
  }
  
  
  
  @Before
  public void importDataSet() throws Exception {
    IDataSet dataSet = readDataSet();
    cleanlyInsertDataset(dataSet);
  }
  
  @BeforeClass
  public static void createSchema() throws Exception {
    RunScript.execute("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                      "sa", "", "schema.sql", null, false);
  }
}
