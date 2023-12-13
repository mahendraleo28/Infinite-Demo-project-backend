package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.District;

public interface DistrictRepository extends JpaRepository<District, Long> {

	List<District> findByStateId(Long stateId);

}
