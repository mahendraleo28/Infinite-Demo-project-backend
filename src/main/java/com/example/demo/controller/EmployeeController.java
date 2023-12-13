package com.example.demo.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/receive-employee")
	public void receiveEmployeeData(@RequestBody Employee employee) {	
		// Process the received employeeData here
		System.out.println("Received Employee Data:");
		System.out.println(employee);
	}

	@GetMapping("/count")
	public long countEmployee() {
		return employeeService.count();
	}

	@GetMapping("/employ")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@PostMapping("/{id}/approve")
	public ResponseEntity<Employee> approveEmployee(@PathVariable Long id) {
		Employee approvedEmployee = employeeService.approveEmployee(id);
		return new ResponseEntity<>(approvedEmployee, HttpStatus.OK);
	}

	@PostMapping("/{id}/reject")
	public ResponseEntity<Employee> rejectEmployee(@PathVariable Long id) {
		Employee rejectedEmployee = employeeService.rejectEmployee(id);
		return new ResponseEntity<>(rejectedEmployee, HttpStatus.OK);
	}

	@PostMapping("/{id}/approve1")
	public ResponseEntity<Employee> approveEmployee1(@PathVariable Long id) {
		Employee approveEmployee1 = employeeService.approveEmployee1(id);
		return new ResponseEntity<>(approveEmployee1, HttpStatus.OK);
	}

	@PostMapping("/{id}/reject1")
	public ResponseEntity<Employee> rejectEmployee1(@PathVariable Long id) {
		Employee rejectedEmployee1 = employeeService.rejectEmployee1(id);
		return new ResponseEntity<>(rejectedEmployee1, HttpStatus.OK);
	}

	@PostMapping("/{id}/comment")
	public ResponseEntity<Employee> addComment(@PathVariable Long id, @RequestBody String comment) {
		Employee employee = employeeService.findById(id);
		employee.setComment(comment);
		Employee updatedEmployee = employeeService.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@PostMapping("/{id}/comment1")
	public ResponseEntity<Employee> addComment1(@PathVariable Long id, @RequestBody String comment1) {
		Employee employee = employeeService.findById(id);
		employee.setComment1(comment1);
		Employee updatedEmployee = employeeService.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@GetMapping("/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}

	@PostMapping("/reg/employ")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		return employeeService.updateEmployee(id, employeeDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
	}

}
