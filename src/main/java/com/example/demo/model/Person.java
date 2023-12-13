package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@JsonIgnore
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private BasicDetails basicDetails;

	@JsonIgnore
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
//    private EducationDetails educationDetails;
	private List<EducationDetails> educationDetailsList = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WorkDetails> workDetailsList = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BasicDetails getBasicDetails() {
		return basicDetails;
	}

	public void setBasicDetails(BasicDetails basicDetails) {
		this.basicDetails = basicDetails;
	}

	public List<EducationDetails> getEducationDetailsList() {
		return educationDetailsList;
	}

	public void setEducationDetailsList(List<EducationDetails> educationDetailsList) {
		this.educationDetailsList = educationDetailsList;
	}

	public List<WorkDetails> getWorkDetailsList() {
		return workDetailsList;
	}

	public void setWorkDetailsList(List<WorkDetails> workDetailsList) {
		this.workDetailsList = workDetailsList;
	}

	// constructor, getters, and setters

}
