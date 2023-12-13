package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.District;
import com.example.demo.service.DistrictService;

@CrossOrigin
@RestController
@RequestMapping("/api/districts")
public class DistrictController {

	@Autowired
	private DistrictService districtService;

	@GetMapping("/byState/{stateId}")
	public List<District> getDistrictsByStateId(@PathVariable Long stateId) {
		return districtService.getDistrictsByStateId(stateId);
	}

}
