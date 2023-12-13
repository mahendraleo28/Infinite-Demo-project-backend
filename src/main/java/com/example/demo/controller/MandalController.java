package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Mandal;
import com.example.demo.service.MandalService;

@CrossOrigin
@RestController
@RequestMapping("/api/mandals")
public class MandalController {

	@Autowired
	private MandalService mandalService;

	@GetMapping("/byDistrict/{districtId}")
	public List<Mandal> getMandalsByDistrictId(@PathVariable Long districtId) {
		return mandalService.getMandalsByDistrictId(districtId);
	}

}
