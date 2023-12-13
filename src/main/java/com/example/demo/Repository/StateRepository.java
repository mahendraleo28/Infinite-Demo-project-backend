package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.State;

public interface StateRepository extends JpaRepository<State, Long> {
	List<State> findByCountryId(Long countryId);
}
