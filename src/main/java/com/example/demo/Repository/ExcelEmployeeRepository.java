package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Excelemploy;

@Repository
public interface ExcelEmployeeRepository extends JpaRepository<Excelemploy, Long> {
	List<Excelemploy> findByCeoCidAndAgeLessThan(String ceoCid, int age);
    boolean existsByMail(String mail);
}
