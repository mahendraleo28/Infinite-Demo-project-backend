package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Village;

public interface VillageRepository extends JpaRepository<Village, Long> {

	List<Village> findByMandalId(Long villageId);

}
