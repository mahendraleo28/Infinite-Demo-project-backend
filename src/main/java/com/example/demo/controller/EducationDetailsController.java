package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Repository.EducationDetailsRepository;
import com.example.demo.Repository.PersonRepository;
import com.example.demo.model.EducationDetails;
import com.example.demo.model.Person;

@CrossOrigin
@RestController
@RequestMapping("/education-details")
public class EducationDetailsController {

	@Autowired
	private EducationDetailsRepository educationDetailsRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/education-details/ed/{id}")
	public ResponseEntity<String> getEducationDetailsByIdd(@PathVariable Long id) {
	    try {
	        // Your code to fetch education details
	        EducationDetails educationDetails = educationDetailsRepository.findById(id).orElse(null);
	        if (educationDetails != null) {
	            return ResponseEntity.status(HttpStatus.ACCEPTED).body("educationDetails");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Education details not found");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching education details.");
	    }
	}


	@SuppressWarnings("deprecation")
	@GetMapping("ed/{id}")
	public ResponseEntity<EducationDetails> getEducationDetailsById1(@PathVariable Long id) {
		try {
			String sql = "SELECT * FROM education_details WHERE id = ?";
			EducationDetails educationDetails = jdbcTemplate.queryForObject(sql, new Object[] { id },
					new BeanPropertyRowMapper<EducationDetails>(EducationDetails.class));
			return ResponseEntity.ok(educationDetails);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education details not found");
		}
	}

	@GetMapping("/{Id}")
	public List<EducationDetails> findByPersonId(@PathVariable Long personId) {
		return educationDetailsRepository.findByPersonId(personId);
	}

	@GetMapping("/{id}")
	public EducationDetails getEducationDetailsById(@PathVariable Long id) {
		Optional<EducationDetails> educationDetailsOptional = educationDetailsRepository.findById(id);
		if (educationDetailsOptional.isPresent()) {
			return educationDetailsOptional.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Basic details not found");
		}
	}

	@PostMapping("/{personId}")
	public ResponseEntity<EducationDetails> addEducationDetails(@PathVariable Long personId,
			@RequestBody EducationDetails educationDetails) {
		Optional<Person> optionalPerson = personRepository.findById(personId);
		if (optionalPerson.isPresent()) {
			Person person = optionalPerson.get();
			educationDetails.setPerson(person);
			EducationDetails savedEducationDetails = educationDetailsRepository.save(educationDetails);
			return ResponseEntity.ok(savedEducationDetails);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
