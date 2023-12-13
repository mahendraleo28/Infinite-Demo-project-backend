package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employees;

@Repository
public interface ExcelRepository extends JpaRepository<Employees, Long> {
	List<Employees> findAll();

}
