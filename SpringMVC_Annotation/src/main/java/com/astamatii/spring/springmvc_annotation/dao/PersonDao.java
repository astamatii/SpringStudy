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
}
