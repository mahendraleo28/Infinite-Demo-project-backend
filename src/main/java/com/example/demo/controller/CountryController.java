package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;

@CrossOrigin
@RestController
@RequestMapping("/api/countries")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping
	public List<Country> getAllCountries() {
		return countryService.getAllCountries();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
		Country country = countryService.getCountryById(id);
		if (country == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(country);
		}
	}

	@PostMapping
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {
		Country addedCountry = countryService.addCountry(country);
		return ResponseEntity.created(URI.create("/api/countries/" + addedCountry.getId())).body(addedCountry);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country country) {
		Country updatedCountry = countryService.updateCountry(id, country);
		if (updatedCountry == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(updatedCountry);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
		boolean deleted = countryService.deleteCountry(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
