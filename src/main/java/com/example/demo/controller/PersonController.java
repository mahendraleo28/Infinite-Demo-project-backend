package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Repository.PersonRepository;
import com.example.demo.model.Person;

@RestController
@CrossOrigin
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@GetMapping("/{id}")
	public Person getPersonById(@PathVariable Long id) {
		Optional<Person> personOptional = personRepository.findById(id);
		if (personOptional.isPresent()) {
			return personOptional.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person details not found");
		}
	}

}
