package com.astamatii.spring.springmvc_annotation.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.astamatii.spring.springmvc_annotation.models.Person;

@Component
public class PersonDao {
	
	private static int PERSON_COUNT;
	private List<Person> people;
	
	{
		people = new ArrayList<>();
		
		people.add(new Person(++PERSON_COUNT, "Tom"));
		people.add(new Person(++PERSON_COUNT, "Jerry"));
		people.add(new Person(++PERSON_COUNT, "Will"));
		people.add(new Person(++PERSON_COUNT, "Kiki"));
	}
	
	public List<Person> index(){
		return people;
	}
	
	public Person show(int id) {
		return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
	}

	public void save(Person person) {
		person.setId(++PERSON_COUNT);
		people.add(person);		
	}
	
	public void update(int id, Person updatedPerson) {
		Person personToBeUpdated = show(id);
		
		personToBeUpdated.setName(updatedPerson.getName());
	}

	public void delete(int id) {
		people.removeIf(person -> person.getId() == id);
		
	}
}
