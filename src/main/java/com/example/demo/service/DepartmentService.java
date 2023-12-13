package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.model.Department;

@Service
public class DepartmentService {

	Logger logger = LoggerFactory.getLogger(DepartmentService.class);

	@Autowired
	private DepartmentRepository departmentRepository;

	public long count() {
		logger.info("A info Message");
		return departmentRepository.count();
	}

	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	public Optional<Department> getDepartmentById(Long id) {
		return departmentRepository.findById(id);
	}

	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department updateDepartment(Long id, Department departmentDetails) {
		Optional<Department> department = departmentRepository.findById(id);
		return departmentRepository.save(department);
	}

	public void deleteDepartment(Long id) {
		@SuppressWarnings("unused")
		Optional<Department> department = departmentRepository.findById(id);
	}

}
