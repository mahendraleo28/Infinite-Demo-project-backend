package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.DistrictRepository;
import com.example.demo.model.District;

@Service
public class DistrictService {

	@Autowired
	private DistrictRepository districtRepository;

	public List<District> getDistrictsByStateId(Long stateId) {
		return districtRepository.findByStateId(stateId);
	}

}
