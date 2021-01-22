package com.example.demo.employee;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

// This is where all the business logic happens
// This is a service class
@Service 
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;
	}
	// returns all employees
	public List<Employee> getEmployees()
	{
		return employeeRepository.findAll();

	}
	
	public void addNewEmployee(Employee employee) {
		Optional<Employee> employeeOptional = employeeRepository
				.findEmployeeByEmail(employee.getEmail());
		
		if (employeeOptional.isPresent()) 
		{
			throw new IllegalStateException("email already exist!");
		}
		employeeRepository.save(employee);
	}
	
	// deleting an employee
	public void deleteEmployee(Long employeeId)
	{
		boolean isFound = employeeRepository.existsById(employeeId);
		
		if(!isFound) 
			{
				throw new IllegalStateException
					("Couldn't find employee with " + employeeId + " in the database!");
			}
		
		employeeRepository.deleteById(employeeId);
	}
	
	// updating employee details
	@Transactional // entity goes into a managed state
	public void updateEmployee(Long employeeId, String email, String name)
	{
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalStateException
					("Employee with " + employeeId + "does not exist!"));
		
		if (name != null && name.length() > 0 
				&& !Objects.equals(employee.getName(), name))
		{
			employee.setName(name);
		}
		
		if (email != null && email.length() > 0 
				&& !Objects.equals(employee.getEmail(), email))
		{
			Optional<Employee> employeeOptional = employeeRepository
					.findEmployeeByEmail(email);
			
			if(employeeOptional.isPresent())
			{
				throw new IllegalStateException("Email is taken!");
			}
			employee.setEmail(email);
		}
		
	}
	
	
}
