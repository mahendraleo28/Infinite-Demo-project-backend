package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.StudentRepository;
import com.example.demo.model.Student;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public long count() {
		return studentRepository.count();
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student getStudentById(Long id) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
		return optionalStudent.isPresent() ? optionalStudent.get() : null;
	}

	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	public Student updateStudent(Long id, Student updatedStudent) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			student.setName(updatedStudent.getName());
			return studentRepository.save(student);
		} else {
			return null;
		}
	}

	public boolean deleteStudent(Long id) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
		if (optionalStudent.isPresent()) {
			studentRepository.delete(optionalStudent.get());
			return true;
		} else {
			return false;
		}
	}
}
