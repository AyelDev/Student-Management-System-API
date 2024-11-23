package com.ayeldev.Student_Management_API.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ayeldev.Student_Management_API.Model.Student;
import com.ayeldev.Student_Management_API.Repository.StudentRepository;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String course) {
		try {
			List<Student> students = new ArrayList<>();

			if (course == null)
				studentRepository.findAll().forEach(students::add);
			else
				studentRepository.findStudentByCourse(course).forEach(students::add);

			if (students.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(students, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") long id) {
		try {
			Optional<Student> studentData = studentRepository.findById(id);

			// The ControllerAdviceClass will be serve as custom http error response as json
			if (studentData.isPresent())
				return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return ResponseEntity.of(studentData);
	}

	@PostMapping("/students")
	public ResponseEntity<?> createStudent(@Valid @RequestBody Student student) {

		Student _student = studentRepository
				.save(new Student(student.getId(), student.getFirstname(), student.getMiddlename(),
						student.getLastname(), student.getSex(),
						student.getEmail(), student.getPhone_number(), student.getDate_of_birth(),
						student.getDate_enrolled(), student.getCourse()));

		return new ResponseEntity<>(_student, HttpStatus.CREATED);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable("id") long id, @Valid @RequestBody Student student) {
		Optional<Student> studentData = studentRepository.findById(id);
		if (studentData.isPresent()) {
			Student _student = studentData.get();
			_student.setFirstname(student.getFirstname());
			_student.setLastname(student.getLastname());
			_student.setMiddlename(student.getMiddlename());
			_student.setEmail(student.getEmail());
			_student.setPhone_number(student.getPhone_number());
			_student.setCourse(student.getCourse());
			_student.setSex(student.getSex());
			_student.setDate_of_birth(student.getDate_of_birth());
			_student.setDate_enrolled(student.getDate_enrolled());

			return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/students")
	public ResponseEntity<?> deleteStudent(@RequestParam(required = false) long id) {

		if (id < 1) {
			studentRepository.deleteAll();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			studentRepository.deleteById(id);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

	}

}