package com.example.demo.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
// setting the path of our employee
@RequestMapping(path= "api/v1/employee")

public class EmployeeController {

	// going to have a reference of EmployeeService and add to constructor
	private final EmployeeService employeeService;
	
	// this employee service should be auto wired for us - instantiated 
	// and injected into the constructor
	@Autowired 
	public EmployeeController(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public List<Employee> getEmployee()
	{
		return employeeService.getEmployees();

	}
	// taking request body and mapping it
	@PostMapping
	public void addEmployee(@RequestBody Employee employee) 
	{
		employeeService.addNewEmployee(employee);
	}
	
	// deleting an employee
	@DeleteMapping(path= "{employeeId}")
	// grabbing the employeeID inside by using @PathVariable
	public void deleteEmployee(@PathVariable("employeeId") Long employeeId)
	{
		employeeService.deleteEmployee(employeeId);
	}
	
	// updating an employee name and email
	@PutMapping(path = "{employeeId}")
	public void updateEmployee(@PathVariable("employeeId") 
					Long employeeId, 
					@RequestParam(required = false) String name,
					@RequestParam(required = false) String email) {
		{
			employeeService.updateEmployee(employeeId, name,email);
		}
	}
}

// API layer talking --->  service layer --> giving back data to API