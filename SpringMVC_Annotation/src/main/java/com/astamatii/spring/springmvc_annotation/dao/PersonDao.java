package com.astamatii.spring.springmvc_annotation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.astamatii.spring.springmvc_annotation.models.Person;

//Here was used JdbcTemplate to manage the Database
@Component
public class PersonDao {

	private static int PERSON_COUNT;
	
	private final JdbcTemplate jdbcTemplate;
	//The object of the class that implements RowMapper created manually
	private final PersonMapper personMapper;
	//The object of the class that implements RowMapper given by spring.
	//We can also use it when the table columns	and class fields 
	//has same names, position and amount (id, name, age, email)
	private final BeanPropertyRowMapper<Person> beanPropertyRowMapper;
	
	@Autowired
	public PersonDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		personMapper = new PersonMapper();
		beanPropertyRowMapper = new BeanPropertyRowMapper<>(Person.class);
	}
	 
	public List<Person> index(){
		//Here we used RowMapper manually created
		return jdbcTemplate.query("SELECT * FROM Person ORDER BY id ASC;", personMapper);		
	}
	
	public Person show(int id) {
		//Here we used RowMapper given by spring
		return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", beanPropertyRowMapper, id)
				.stream().findAny().orElse(null);		
	}

	public void save(Person person) {		
		jdbcTemplate.update("INSERT INTO Person (name, age, email) VALUES(?,?,?)", person.getName(), person.getAge(), person.getEmail());
	}
	
	public void update(int id, Person person) {
		jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", person.getName(), person.getAge(), person.getEmail(), id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
	}
}

/* That`s PersonDao using JDBC API 
 *  
 * @Component public class PersonDao {
 * 
 * private static int PERSON_COUNT; private static final String URL =
 * "jdbc:postgresql://192.168.48.129:5432/spring"; private static final String
 * USERNAME = "spring"; private static final String PASSWORD = "password";
 * 
 * private static Connection connection;
 * 
 * static { try { Class.forName("org.postgresql.Driver"); } catch
 * (ClassNotFoundException e) { e.printStackTrace(); }
 * 
 * try { connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); }
 * catch (SQLException e) { e.printStackTrace(); } }
 * 
 * public List<Person> index(){
 * 
 * List<Person> people = new ArrayList<>();
 * 
 * try { Statement statement = connection.createStatement(); //Our SQL Query
 * String SQL = "SELECT * FROM Person ORDER BY id;"; //The result of our SQL
 * Query; executeQuery - only gets the data ResultSet resultSet =
 * statement.executeQuery(SQL);
 * 
 * PERSON_COUNT = 0;
 * 
 * while(resultSet.next()) { Person person = new Person();
 * 
 * person.setId(resultSet.getInt("id"));
 * person.setName(resultSet.getString("name"));
 * person.setAge(resultSet.getInt("age"));
 * person.setEmail(resultSet.getString("email"));
 * 
 * people.add(person);
 * 
 * PERSON_COUNT++; } } catch (SQLException e) { e.printStackTrace(); }
 * 
 * return people; }
 * 
 * public Person show(int id) { Person person = null;
 * 
 * try { PreparedStatement preparedStatement =
 * connection.prepareStatement("SELECT * FROM Person WHERE id=?");
 * 
 * preparedStatement.setInt(1, id); ResultSet resultSet =
 * preparedStatement.executeQuery();
 * 
 * //Will return first found row from SQL query result resultSet.next();
 * 
 * person = new Person(); person.setId(resultSet.getInt("id"));
 * person.setName(resultSet.getString("name"));
 * person.setAge(resultSet.getInt("age"));
 * person.setEmail(resultSet.getString("email"));
 * 
 * } catch (SQLException e) { e.printStackTrace(); }
 * 
 * return person; }
 * 
 * public void save(Person person) { person.setId(++PERSON_COUNT);
 * 
 * try { PreparedStatement preparedStatement =
 * connection.prepareStatement("INSERT INTO Person VALUES(?,?,?,?)");
 * 
 * preparedStatement.setInt(1, PERSON_COUNT); preparedStatement.setString(2,
 * person.getName()); preparedStatement.setInt(3, person.getAge());
 * preparedStatement.setString(4, person.getEmail());
 * preparedStatement.executeUpdate();
 * 
 * } catch (SQLException e) { e.printStackTrace(); }
 * 
 * 
 * // Manual SQL update query - That`s not recommended, only for study purposes.
 * // try { Statement statement = connection.createStatement(); //Our SQL Query
 * // String SQL = "INSERT INTO Person VALUES(" + person.getId() + ",'" + //
 * person.getName() + "'," + person.getAge() + ",'" + person.getEmail() +"');";
 * //The result of our SQL Query; executeUpdate - does data changes into DB //
 * statement.executeUpdate(SQL); // } catch (SQLException e) {
 * e.printStackTrace(); }
 * 
 * }
 * 
 * public void update(int id, Person updatedPerson) { try { PreparedStatement
 * preparedStatement = connection.
 * prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");
 * 
 * preparedStatement.setString(1, updatedPerson.getName());
 * preparedStatement.setInt(2, updatedPerson.getAge());
 * preparedStatement.setString(3, updatedPerson.getEmail());
 * preparedStatement.setInt(4, id); preparedStatement.executeUpdate();
 * 
 * } catch (SQLException e) { e.printStackTrace(); } }
 * 
 * public void delete(int id) { try { PreparedStatement preparedStatement =
 * connection.prepareStatement("DELETE FROM Person WHERE id=?");
 * 
 * preparedStatement.setInt(1, id); preparedStatement.executeUpdate();
 * 
 * } catch (SQLException e) { e.printStackTrace(); } } }
 */

/* That was PersonDao class before i`ve added PostgreSQL Database
 * 
 * @Component public class PersonDao {
 * 
 * private List<Person> people;
 * 
 * { people = new ArrayList<>();
 * 
 * people.add(new Person(++PERSON_COUNT, "Tom", 10, "tom@email.com"));
 * people.add(new Person(++PERSON_COUNT, "Jerry", 5, "jerry@email.com"));
 * people.add(new Person(++PERSON_COUNT, "Will", 30, "will@email.com"));
 * people.add(new Person(++PERSON_COUNT, "Kiki", 15, "kiki@email.com")); }
 * 
 * private static int PERSON_COUNT;
 * 
 * public List<Person> index(){ return people; }
 * 
 * public Person show(int id) { return people.stream().filter(person ->
 * person.getId() == id).findAny().orElse(null); }
 * 
 * public void save(Person person) { people.add(person); }
 * 
 * public void update(int id, Person updatedPerson) { Person personToBeUpdated =
 * show(id);
 * 
 * personToBeUpdated.setName(updatedPerson.getName());
 * personToBeUpdated.setAge(updatedPerson.getAge());
 * personToBeUpdated.setEmail(updatedPerson.getEmail());
 * 
 * }
 * 
 * public void delete(int id) { people.removeIf(person -> person.getId() == id);
 * } 
 * }
 */
