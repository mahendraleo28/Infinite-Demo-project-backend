package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findById(Person id);
}