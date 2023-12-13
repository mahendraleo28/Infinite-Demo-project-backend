package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Employee;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public long count() {
		return employeeRepository.count();
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Long id, Employee employeeDetails) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			employee.setName(employeeDetails.getName());
			employee.setEmail(employeeDetails.getEmail());
			return employeeRepository.save(employee);
		} else {
			return null;
		}
	}

	public void deleteEmployee(Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			employeeRepository.delete(employee);
		}
	}

	@SuppressWarnings("static-access")
	public Employee approveEmployee(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
		employee.setStatus(employee.APPROVED);
		return employeeRepository.save(employee);
	}

	@SuppressWarnings("static-access")
	public Employee approveEmployee1(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
		employee.setAdmin_status(employee.APPROVED);
		return employeeRepository.save(employee);
	}

	public Employee rejectEmployee(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
		employee.setStatus(Employee.REJECTED);
		return employeeRepository.save(employee);
	}

	public Employee rejectEmployee1(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
		employee.setAdmin_status(Employee.REJECTED);
		return employeeRepository.save(employee);
	}

	public Employee saveComment(Employee comment) {
		return employeeRepository.save(comment);
	}

	@SuppressWarnings("deprecation")
	public Employee findById(Long id) {
		return employeeRepository.getById(id);
	}

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
}
