package com.astamatii.spring.snippets.springboot.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.astamatii.spring.snippets.springboot.model.Student;
import com.astamatii.spring.snippets.springboot.repositories.StudentRepository;

@Configuration
public class StudentConfig {
	
	@Bean //(this bean inserts 2 students to DB at the start this app)
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args ->{
			Student mary = new Student(
					"Mary",
					"mary@gmail.com",
					LocalDate.of(2000, Month.JANUARY, 5)
					);
			Student alex = new Student(
					"Alex",
					"alex@gmail.com",
					LocalDate.of(2004, Month.JANUARY, 5)
					);
			
			repository.saveAll(
					List.of(mary, alex)
					);
		};
	}
}
