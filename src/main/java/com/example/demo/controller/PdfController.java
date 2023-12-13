package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.PdfService;
import com.example.demo.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/pdf")
public class PdfController {
	@Autowired
	private UserService userService;

	@Autowired
	private PdfService pdfService;

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getUsersReport() {
		List<User> users = userService.getAllUsers();

		ByteArrayInputStream bis = pdfService.generatePdf(users);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=users.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
