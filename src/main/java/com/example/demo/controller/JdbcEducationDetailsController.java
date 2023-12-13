package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.EducationDetails;

@RestController
@RequestMapping("/jdbc/education-details")
public class JdbcEducationDetailsController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<EducationDetails> getEducationDetailsById(@PathVariable Long id) {
        try {
            String sql = "SELECT * FROM education_details WHERE id = ?";
            @SuppressWarnings("deprecation")
			EducationDetails educationDetails = jdbcTemplate.queryForObject(sql, new Object[] { id },
                    new BeanPropertyRowMapper<EducationDetails>(EducationDetails.class));
            return ResponseEntity.ok(educationDetails);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education details not found");
        }
    }

}
