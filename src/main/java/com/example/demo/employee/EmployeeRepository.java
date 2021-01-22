package com.example.demo.employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository 
			extends JpaRepository<Employee, Long>{
	
//	another way to get employee by email
//	@Query("SELECT s FROM Employee s WHERE s.email = ?1")
	Optional<Employee> findEmployeeByEmail(String email);
}

