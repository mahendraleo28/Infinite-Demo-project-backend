package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.MandalRepository;
import com.example.demo.model.Mandal;

@Service
public class MandalService {

	@Autowired
	private MandalRepository mandalRepository;

	public List<Mandal> getMandalsByDistrictId(Long districtId) {
		return mandalRepository.findByDistrictId(districtId);
	}

}
