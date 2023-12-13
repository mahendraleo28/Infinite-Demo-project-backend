package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Repository.WorkDetailsRepository;
import com.example.demo.model.WorkDetails;

@CrossOrigin
@RestController
@RequestMapping("/work-details")
public class WorkDetailsController {

	@Autowired
	private WorkDetailsRepository workDetailsRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@GetMapping("wd/{id}")
	public ResponseEntity<WorkDetails> getWorkDetailsByPersonId(@PathVariable Long id) {
		try {
			String sql = "SELECT * FROM work_details WHERE id = ?";
			WorkDetails workDetails = jdbcTemplate.queryForObject(sql, new Object[] { id },
					new BeanPropertyRowMapper<WorkDetails>(WorkDetails.class));
			return ResponseEntity.ok(workDetails);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Work Details not found");
		}
	}

	@GetMapping("/{id}")
	public List<WorkDetails> findByPersonId(@PathVariable Long id) {
		return workDetailsRepository.findByPersonId(id);
	}
}
