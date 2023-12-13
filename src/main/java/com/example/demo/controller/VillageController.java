package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Village;
import com.example.demo.service.VillageService;

@CrossOrigin
@RestController
@RequestMapping("/api/villages")
public class VillageController {

	@Autowired
	private VillageService villageService;

	@GetMapping("/byMandal/{mandalId}")
	public List<Village> getVillagesByMandalId(@PathVariable Long mandalId) {
		return villageService.getVillagesByMandalId(mandalId);
	}

}
