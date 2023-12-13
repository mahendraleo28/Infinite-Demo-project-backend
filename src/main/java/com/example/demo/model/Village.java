package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "villages")
public class Village {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mandal_id", nullable = false)
	private Mandal mandal;

	public Village() {
	}

	public Village(String name, Mandal mandal) {
		this.name = name;
		this.mandal = mandal;
	}

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

	public Mandal getMandal() {
		return mandal;
	}

	public void setMandal(Mandal mandal) {
		this.mandal = mandal;
	}

	@Override
	public String toString() {
		return "Village [id=" + id + ", name=" + name + ", mandal=" + mandal + "]";
	}

	public Village(Long id, String name, Mandal mandal) {
		super();
		this.id = id;
		this.name = name;
		this.mandal = mandal;
	}

	// Getters and setters
	// ...
}
