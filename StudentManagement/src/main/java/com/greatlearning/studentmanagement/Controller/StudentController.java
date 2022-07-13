package com.greatlearning.studentmanagement.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.studentmanagement.Entity.Student;
import com.greatlearning.studentmanagement.Service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/add-begin")
	public String handleAddStudentBegin(Model model) {

		// create model attribute to bind form data
		Student student = new Student();

		model.addAttribute("student", student);

		return "student-details";
	}
	
	@RequestMapping("/update-begin")
	public String handleUpdateStudentBegin(
		@RequestParam("studentId") int studentId, 
		Model model) {

		Student student = studentService.findById(studentId);

		model.addAttribute("student", student);

		return "student-details";
	}

	
	@PostMapping("/save")
	public String saveStudent(
		@RequestParam("studentId") int studentId, 
		@RequestParam("firstName") String firstName,
		@RequestParam("lastName") String lastName, 
		@RequestParam("course") String course,
		@RequestParam("country") String country) {

		System.out.println(studentId);
		Student student = null;
		if (studentId != 0) {
			
			student = studentService.findById(studentId);
			
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setCourse(course);
			student.setCountry(country);
			
		} else{
			
			// Add flow
			student = new Student(firstName, lastName, course, country);
		}
		
		studentService.save(student);

		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list";

	}	
	
	@RequestMapping("/delete")
	public String delete(
		@RequestParam("studentId") int studentId) {

		studentService.deleteById(studentId);

		return "redirect:/students/list";

	}


}
