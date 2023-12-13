package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.WorkDetails;

public interface WorkDetailsRepository extends JpaRepository<WorkDetails, Long> {

	List<WorkDetails> findByPersonId(Long personId);

}
