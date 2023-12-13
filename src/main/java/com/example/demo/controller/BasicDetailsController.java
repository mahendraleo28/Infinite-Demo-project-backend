package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Repository.BasicDetailsRepository;
import com.example.demo.model.BasicDetails;

@CrossOrigin
@RestController
@RequestMapping("/basic-details")
public class BasicDetailsController {

	@Autowired
	private BasicDetailsRepository basicDetailsRepository;

	@GetMapping("/{id}")
	public BasicDetails getBasicDetailsById(@PathVariable Long id) {
		Optional<BasicDetails> basicDetailsOptional = basicDetailsRepository.findById(id);
		if (basicDetailsOptional.isPresent()) {
			return basicDetailsOptional.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Basic details not found");
		}
	}
	
	@GetMapping("/all")
	public List<BasicDetails> getAllBasicDetails() {
	    List<BasicDetails> allBasicDetails = basicDetailsRepository.findAll();
	    return allBasicDetails;
	}
	
	@PostMapping("/")
	public BasicDetails createBasicDetails(@RequestBody BasicDetails basicDetails) {
		return basicDetailsRepository.save(basicDetails);
	}
	
	@PutMapping("/{id}")
	public BasicDetails updateBasicDetails(@PathVariable Long id, @RequestBody BasicDetails basicDetails) {
		Optional<BasicDetails> existingBasicDetailsOptional = basicDetailsRepository.findById(id);
		if (existingBasicDetailsOptional.isPresent()) {
			basicDetails.setPersonId(id);
			return basicDetailsRepository.save(basicDetails);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Basic details not found");
		}
	}

	@DeleteMapping("/{id}")
	public void deleteBasicDetails(@PathVariable Long id) {
		Optional<BasicDetails> existingBasicDetailsOptional = basicDetailsRepository.findById(id);
		if (existingBasicDetailsOptional.isPresent()) {
			basicDetailsRepository.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Basic details not found");
		}
	}
}
