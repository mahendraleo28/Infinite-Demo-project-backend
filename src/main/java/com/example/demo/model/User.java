package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "username", nullable = false, unique = true)
	@Size(min = 8, message = "Username must be at least 8 characters long")
	private String username;

	@Column(name = "password", nullable = false)
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;

	@Column(nullable = false)
	private String company;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String Role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", company=" + company
				+ ", email=" + email + "]";
	}

	public User(int id, @Size(min = 8, message = "Username must be at least 8 characters long") String username,
			@Size(min = 8, message = "Password must be at least 8 characters long") String password, String company,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.company = company;
		this.email = email;
	}

	public User() {
		super();
	}
}