package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "fname")
	private String fname;

	@Column(name = "email")
	private String email;

	@Column(name = "lname")
	private String lname;

	public Employees() {
	}

	public Employees(String name, String email, String phone) {
		this.fname = name;
		this.email = email;
		this.lname = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return fname;
	}

	public void setName(String name) {
		this.fname = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return lname;
	}

	public void setPhone(String phone) {
		this.lname = phone;
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + id + ", name='" + fname + '\'' + ", email='" + email + '\'' + ", phone='" + lname
				+ '\'' + '}';
	}

	public void setValue(double numericCellValue) {
		// TODO Auto-generated method stub

	}
}
