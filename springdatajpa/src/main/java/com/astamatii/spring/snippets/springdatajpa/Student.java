package com.astamatii.spring.snippets.springdatajpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Student")
@Table(
		name = "student",
		uniqueConstraints = {
				@UniqueConstraint(name = "student_email_unique", columnNames = "email")
		}
		)
public class Student  { // To make a working Entity we need 1. @Entity annotation, 2. @Id field, 3. An empty constructor (records won`t be saved without it)
	
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize =  1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	@Column(
			name = "id", 
			updatable = false
			)
	private Long id;
	@Column(
			name = "first_name", 
			nullable = false, 
			columnDefinition = "TEXT"
			)
	private String firstname;
	@Column(
			name = "last_name", 
			nullable = false, 
			columnDefinition = "TEXT"
			)
	private String lastname;
	@Column(
			name = "email", 
			nullable = false, 
			columnDefinition = "TEXT" //, 
//			unique = true // it has priority over manually created constraints inside the @Table()
			)
	private String email;
	@Column(
			name = "age"
			)
	private Integer age;
	
	public Student() {}
	
	public Student(String firstname, String lastname, String email, Integer age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
