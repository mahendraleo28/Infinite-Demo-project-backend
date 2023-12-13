package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@CrossOrigin
public class RegistrationController {

	Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getPassword() == null
				|| user.getPassword().trim().isEmpty() || user.getCompany() == null || user.getEmail() == null
				|| user.getRole() == null) {
			logger.trace("A info Message");
		}

		if (userService.findUserByUsername(user.getUsername()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
		} else {
			logger.trace("A TRACE Message");
			userService.saveUser(user);
			return ResponseEntity.ok("User " + user.getUsername() + " registered successfully");

		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestParam String username) {
		User user = userService.findByUsername(username);

		if (user != null) {
			return ResponseEntity.ok("Login successful");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	@PutMapping("/user/{username}")
	public ResponseEntity<?> updateUserByUsername(@PathVariable String username, @RequestBody User user) {
		User existingUser = userService.findByUsername(username);

		if (existingUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		existingUser.setPassword(user.getPassword());
		existingUser.setEmail(user.getEmail());

		User savedUser = userService.save(existingUser);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}

	@DeleteMapping("/user/{username}")
	public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
		User user = userService.findByUsername(username);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userService.deleteByUsername(username);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity<?> getUserByUsernameAndPassword(@RequestBody User user) {
		User foundUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (foundUser != null) {
			return new ResponseEntity<>(foundUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.findAll();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
}