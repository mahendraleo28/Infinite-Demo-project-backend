package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/count")
	public long countStudents() {
		return studentService.count();
	}

	@GetMapping
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
		Student student = studentService.getStudentById(id);
		if (student != null) {
			return ResponseEntity.ok(student);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student newStudent = studentService.createStudent(student);
		return ResponseEntity.ok(newStudent);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
		Student updatedStudent = studentService.updateStudent(id, student);
		if (updatedStudent != null) {
			return ResponseEntity.ok(updatedStudent);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		boolean success = studentService.deleteStudent(id);
		if (success) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
