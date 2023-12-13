package com.example.demo.model;

import com.example.demo.Repository.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String phone;
	private String email;
	private String status;
	private String admin_status;
	private String comment;
	private String comment1;

	public static final String APPROVED = "Approved";
	public static final String REJECTED = "Rejected";

	public Long getId() {
		return id;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public String getAdmin_status() {
		return admin_status;
	}

	public void setAdmin_status(String admin_status) {
		this.admin_status = admin_status;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public static String getApproved() {
		return APPROVED;
	}

	public static String getRejected() {
		return REJECTED;
	}

	public void setStatus(Status approved2) {
		// TODO Auto-generated method stub

	}

}