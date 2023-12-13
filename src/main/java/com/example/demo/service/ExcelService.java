package com.example.demo.service;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Repository.ExcelRepository;
import com.example.demo.model.Employees;

@Service
public class ExcelService {

	@Autowired
	private ExcelRepository excelRepository;

	public void saveDataFromExcelFile(MultipartFile file) throws Exception {
		Logger logger = LoggerFactory.getLogger(ExcelService.class);
		try (InputStream inputStream = file.getInputStream()) {
			logger.warn("an warning message");
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 0) {
					continue;
				}
				Employees entity = new Employees();
				entity.setId((int) row.getCell(0).getNumericCellValue());
				entity.setName(row.getCell(1).getStringCellValue());
				entity.setEmail(row.getCell(2).getStringCellValue());
				entity.setPhone(row.getCell(3).getStringCellValue());
				excelRepository.save(entity);
			}
		}
	}

	public List<Employees> getAllEmployees() {
		return excelRepository.findAll();
	}

}
