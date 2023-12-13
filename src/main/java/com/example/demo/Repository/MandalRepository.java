package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Mandal;

public interface MandalRepository extends JpaRepository<Mandal, Long> {

	List<Mandal> findByDistrictId(Long mandalId);

}
