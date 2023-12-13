package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.VillageRepository;
import com.example.demo.model.Village;

@Service
public class VillageService {

	@Autowired
	private VillageRepository villageRepository;

	public List<Village> getVillagesByMandalId(Long mandalId) {
		return villageRepository.findByMandalId(mandalId);
	}

}
