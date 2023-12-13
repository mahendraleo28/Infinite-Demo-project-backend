package com.example.demo.model;

import java.util.List;

public class ExcelUploadResponse {
	private String status;
	private List<String> errors;
	private List<Excelemploy> uploadedData;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public List<Excelemploy> getUploadedData() {
		return uploadedData;
	}

	public void setUploadedData(List<Excelemploy> uploadedData) {
		this.uploadedData = uploadedData;
	}

	@Override
	public String toString() {
		return "ExcelUploadResponse [status=" + status + ", errors=" + errors + ", uploadedData=" + uploadedData + "]";
	}

}