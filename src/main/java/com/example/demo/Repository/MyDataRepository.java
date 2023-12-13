package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MyData;

public interface MyDataRepository extends JpaRepository<MyData, Long> {
}
