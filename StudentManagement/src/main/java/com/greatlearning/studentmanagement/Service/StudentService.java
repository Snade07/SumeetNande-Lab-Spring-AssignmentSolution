package com.greatlearning.studentmanagement.Service;


import java.util.List;

import com.greatlearning.studentmanagement.Entity.Student;


public interface StudentService {

	List<Student> findAll();
	
	void save(Student thestudent);
	
	void deleteById(int theId);

	Student findById(int theId);
	
}




