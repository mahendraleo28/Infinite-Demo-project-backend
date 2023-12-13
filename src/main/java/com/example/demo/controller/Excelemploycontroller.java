package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Excelemploy;
import com.example.demo.service.ExcelEmployService;

@RestController
@CrossOrigin
@RequestMapping("/api/excelemploy")
public class Excelemploycontroller {

	@Autowired
	private ExcelEmployService excelEmployService;
	
	private String recipientEmail;

	//This method is used to store the email address
	@PostMapping("/store-email")
    public ResponseEntity<String> storeEmail(@RequestBody Map<String, String> request) {
        recipientEmail = request.get("recipientEmail");

        if (recipientEmail == null || recipientEmail.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid email address");
        } else {
            // Call the service method and pass the recipientEmail
        	excelEmployService.processRecipientEmail(recipientEmail);
            return ResponseEntity.ok("Email stored and processed successfully");
        }
    }
	// This method is used to upload the Excel file
	@PostMapping("/upload")
	public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
		try {
			excelEmployService.saveDataFromExcelFile(file);
			return ResponseEntity.ok("Excel data has been successfully uploaded and processed.");
		} catch (Exception e) {
//			String errorMessage = "Error processing Excel file: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

	// it is fetching all the data of employees
	@GetMapping("/list")
	public List<Excelemploy> getAllEmployees() {
		return excelEmployService.getAllEmployees();
	}

	// This method is used to filter the employees based on coecid == Java and age
	// less than 25
	@GetMapping("/filtered-employees")
	public List<Excelemploy> getFilteredEmployees() {
		return excelEmployService.getEmployeesWithCeoCidAndAgeLessThan25();
	}

	// This method is used to store the Warnings which are null rows in the excel
	// file
	@GetMapping("/warnings")
	public ResponseEntity<List<String>> getWarnings1() {
		List<String> warnings = excelEmployService.getWarnings();
		return new ResponseEntity<>(warnings, HttpStatus.OK);
	}

}
