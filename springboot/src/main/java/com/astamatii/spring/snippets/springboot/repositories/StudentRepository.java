package com.astamatii.spring.snippets.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.astamatii.spring.snippets.springboot.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
//	@Query("select s from Student s where s.email = ?1") // (same thing) 
	Optional<Student> findStudentByEmail(String email);
	
	boolean existsByEmail(String email);
}
