package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findUserByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User user(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public void deleteByUsername(String username) {
		userRepository.delete(userRepository.findByUsername(username));
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public User findById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	public User deleteById(int id) {
		// TODO Auto-generated method stub
		return userRepository.deleteById(id);

	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

}
