package com.example.demo.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Image;
import com.example.demo.service.ImageService;

@RestController
@CrossOrigin
public class ImageController {

	@Autowired
	private ImageService imageService;

	@PostMapping("/images")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		imageService.saveImage(file);
		return ResponseEntity.ok("Image uploaded successfully");
	}

	@GetMapping("/images/{id}")
	public ResponseEntity<String> getImage(@PathVariable("id") Long id) {
		Image image = imageService.getImageById(id);
		if (image == null) {
			return ResponseEntity.notFound().build();
		}
		String imageData = Base64.getEncoder().encodeToString(image.getData());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
				.body(imageData);
	}

	@GetMapping("/imagess/{name}")
	public ResponseEntity<String> getImage(@PathVariable("name") String name) {
		Image image = imageService.getImageByName(name);
		if (image == null) {
			return ResponseEntity.notFound().build();
		}
		String imageData = Base64.getEncoder().encodeToString(image.getData());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
				.body(imageData);
	}

}
