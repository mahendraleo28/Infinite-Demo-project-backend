package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "my_table")
public class MyData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "column_1")
	private String column1;

	@Column(name = "column_2")
	private String column2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public MyData(Long id, String column1, String column2) {
		super();
		this.id = id;
		this.column1 = column1;
		this.column2 = column2;
	}

	public MyData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MyData [id=" + id + ", column1=" + column1 + ", column2=" + column2 + "]";
	}

	// getters and setters

}
