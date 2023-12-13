package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.EducationDetails;

public interface EducationDetailsRepository extends JpaRepository<EducationDetails, Long> {

	List<EducationDetails> findByPersonId(Long personId);
}