package com.example.demo.employee;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {
	
	@Bean
	CommandLineRunner clr (EmployeeRepository repository) {
		return args -> {
			Employee Zahid = new Employee(
					"Zahid",
					"zahidali@gmail.com",
					LocalDate.of(2000, 6, 18)
					);
		
			repository.saveAll(List.of(Zahid));
		};
		
	}
}
