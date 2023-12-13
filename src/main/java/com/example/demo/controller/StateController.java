package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.State;
import com.example.demo.service.StateService;

@CrossOrigin
@RestController
@RequestMapping("/api/states")
public class StateController {

	@Autowired
	private StateService stateService;

	@GetMapping("/byCountry/{countryId}")
	public List<State> getStatesByCountryId(@PathVariable Long countryId) {
		return stateService.getStatesByCountryId(countryId);
	}

}
