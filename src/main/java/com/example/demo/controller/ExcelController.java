package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Employees;
import com.example.demo.service.ExcelService;

@RestController
@CrossOrigin
public class ExcelController {

	@Autowired
	private ExcelService excelService;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
		try {
			excelService.saveDataFromExcelFile(file);
			return ResponseEntity.ok("Data saved successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while saving data: " + e.getMessage());
		}

	}

	@GetMapping("/employees")
	public List<Employees> getAllEmployees() {
		return excelService.getAllEmployees();
	}

}
