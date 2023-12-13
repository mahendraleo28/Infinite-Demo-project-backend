package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;

@Entity
public class Excelemploy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String ceoCid;
	@Column(nullable = false)
	private Date dateOfBirth;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false, unique = true)
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
	private String mail;
	@Column(name = "eligibilityStatus")
	private String eligibilityStatus;

	public Excelemploy() {

	}

	public Excelemploy(String firstName, String lastName, String ceoCid, Date dateOfBirth, int age, String mail,
			String eligibilityStatus) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.ceoCid = ceoCid;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.mail = mail;
		this.eligibilityStatus = eligibilityStatus;
	}

	@Override
	public String toString() {
		return "Excelemploy [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", ceoCid=" + ceoCid
				+ ", dateOfBirth=" + dateOfBirth + ", age=" + age + ", mail=" + mail + ", eligibilityStatus="
				+ eligibilityStatus + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCeoCid() {
		return ceoCid;
	}

	public void setCeoCid(String ceoCid) {
		this.ceoCid = ceoCid;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getEligibilityStatus() {
		return eligibilityStatus;
	}

	public void setEligibilityStatus(String eligibilityStatus) {
		this.eligibilityStatus = eligibilityStatus;
	}
}
