package com.ayeldev.Student_Management_API.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayeldev.Student_Management_API.Model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	List<Student> findStudentByCourse(String course);
}