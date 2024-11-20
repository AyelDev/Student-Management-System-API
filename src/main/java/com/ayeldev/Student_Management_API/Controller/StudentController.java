package com.ayeldev.Student_Management_API.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayeldev.Student_Management_API.Model.Student;
import com.ayeldev.Student_Management_API.Repository.StudentRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/students")
	public ResponseEntity<?> getAllStudents(@RequestParam(required = false) String course){
		try {
			List<Student> students = new ArrayList<>();
			
			if(course == null)
				studentRepository.findAll().forEach(students::add);
			else
				studentRepository.findStudentByCourse(course).forEach(students::add);
			
			if(students.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<>(students, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
