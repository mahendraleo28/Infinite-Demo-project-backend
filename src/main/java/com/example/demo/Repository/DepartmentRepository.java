package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department save(Optional<Department> department);

}