package com.example.demo.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Repository.MyDataRepository;
import com.example.demo.model.MyData;

@RestController
@RequestMapping("/api")
public class MyController {
	@Autowired
	private MyDataRepository myDataRepository;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			// read Excel sheet
			@SuppressWarnings("resource")
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);

			// iterate over rows and insert data into database
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					// skip header row
					continue;
				}

				MyData myData = new MyData();
				myData.setColumn1(row.getCell(0).getStringCellValue());
				myData.setColumn2(row.getCell(1).getStringCellValue());
				myDataRepository.save(myData);
			}

			return ResponseEntity.ok("File uploaded successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
		}
	}
}
