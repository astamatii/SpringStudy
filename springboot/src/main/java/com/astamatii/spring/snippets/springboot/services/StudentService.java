package com.astamatii.spring.snippets.springboot.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astamatii.spring.snippets.springboot.model.Student;
import com.astamatii.spring.snippets.springboot.repositories.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();			
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail()); // SELECT s1_0.id, s1_0.dob, s1_0.email, s1_0.name FROM student s1_0 WHERE s1_0.email=?
		if (studentOptional.isPresent()) 
			throw new IllegalStateException("email taken");
		
		studentRepository.save(student); // SELECT nextval('student_sequence')
										 // INSERT INTO student (dob,email,name,id) VALUES (?,?,?,?)
	}

	public void deleteStudent(Long studentId) {
		boolean studentExists = studentRepository.existsById(studentId); // SELECT count(*) FROM student s1_0 WHERE s1_0.id=?
		if (!studentExists) {
			throw new IllegalStateException(
					"student with id " + studentId + " does not exists");
		}
		
		studentRepository.deleteById(studentId); // SELECT s1_0.id, s1_0.dob, s1_0.email, s1_0.name FROM student s1_0 WHERE s1_0.id=?
												 // DELETE FROM student WHERE id=?
	}
	
	@Transactional // ENTITY is in the MANAGED STATE
				   // (it tells that you don`t have to implement any JPQL query, 
				   //	and can use your setters from your Entity, 
				   //	check whether you can or cannot update,
				   //	and automatically update the database)
	public void updateStudent(Long studentId,
								String name,
								String email) {
		Student student = studentRepository.findById(studentId) // SELECT s1_0.id, s1_0.dob, s1_0.email, s1_0.name FROM student s1_0 WHERE s1_0.id=?
				.orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));
		
		if (name != null &&
				name.length() > 0 &&
				!Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		if (email != null &&
				email.length() > 0 &&
				!Objects.equals(student.getEmail(), email)) {
			boolean emailExists = studentRepository.existsByEmail(email); // SELECT s1_0.id FROM student s1_0 WHERE s1_0.email=? FETCH FIRST ? ROWS ONLY
			if (emailExists) 
				throw new IllegalStateException("email taken");
			student.setEmail(email);
		}
	} // UPDATE student SET dob=?, email=?, name=? WHERE id=?
	  // (if Exceptions - Transaction CANCELED)
	
}
