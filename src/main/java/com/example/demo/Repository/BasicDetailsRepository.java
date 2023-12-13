package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BasicDetails;

public interface BasicDetailsRepository extends JpaRepository<BasicDetails, Long> {


}
