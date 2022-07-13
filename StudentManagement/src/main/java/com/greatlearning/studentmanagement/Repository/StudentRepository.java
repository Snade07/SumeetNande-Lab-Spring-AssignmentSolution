package com.greatlearning.studentmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.studentmanagement.Entity.Student;

public interface StudentRepository 
	extends JpaRepository<Student, Integer>{

}
