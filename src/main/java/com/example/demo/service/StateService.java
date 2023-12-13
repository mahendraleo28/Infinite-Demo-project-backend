package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.StateRepository;
import com.example.demo.model.State;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	public List<State> getStatesByCountryId(Long countryId) {
		return stateRepository.findByCountryId(countryId);
	}

}
