package com.example.demo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Repository.ImageRepository;
import com.example.demo.model.Image;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public void saveImage(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		byte[] data = file.getBytes();
		Image image = new Image();
		image.setName(fileName);
		image.setData(data);
		imageRepository.save(image);
	}

	public Image getImageById(Long id) {
		return imageRepository.findById(id).orElse(null);
	}

	public Image getImageByName(String name) {
		return imageRepository.findByName(name);
	}

}
