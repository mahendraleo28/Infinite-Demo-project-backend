package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("/departments")
@CrossOrigin
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/count")
	public long countEmployee() {
		return departmentService.count();
	}

	@GetMapping
	public List<Department> getAllDepartments() {
		return departmentService.getAllDepartments();
	}

	@GetMapping("/{id}")
	public Optional<Department> getDepartmentById(@PathVariable Long id) {
		return departmentService.getDepartmentById(id);
	}

	@PostMapping
	public Department createDepartment(@RequestBody Department department) {
		return departmentService.createDepartment(department);
	}

	@PutMapping("/{id}")
	public Department updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
		return departmentService.updateDepartment(id, departmentDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
	}

	@GetMapping("/departments/pdf")
	public ResponseEntity<byte[]> generateDepartmentReport() throws Exception {
		List<Department> departments = departmentService.getAllDepartments();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, outputStream);
		document.open();

		PdfPTable table = new PdfPTable(2);
		addTableHeader(table);
		for (Department department : departments) {
			addTableRow(table, department.getId(), department.getName());
		}
		document.add(table);

		document.close();
		byte[] pdfBytes = outputStream.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("inline", "departments.pdf");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
		return response;
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("Name", "Location").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addTableRow(PdfPTable table, long id, String name) {
		table.addCell(String.valueOf(id));
		table.addCell(name);
	}

}
