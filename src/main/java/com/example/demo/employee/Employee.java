package com.example.demo.employee;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity // this is for hibernate
@Table // table in the database
// This is the employee model where all the data about employee is stored
public class Employee {
	
	@Id
	@SequenceGenerator 
		(
			name = "employee_sequence",
			sequenceName = "employee_sequence",
			allocationSize = 1
		)
	@GeneratedValue
		(
			strategy = GenerationType.SEQUENCE,
			generator = "employee_sequence"
					
		)
	
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	 @Transient // means no need to have a colunm in dattabase
	private Integer age;

	// employee default constructor
	public Employee(){
		
	}
	// employee constructor with parameters
	public Employee(
			Long id, String name,
			String email, LocalDate 
			dob) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
		//this.age = age;
	}
	// employee constructor with parameters and No id as it will be generated by database
	public Employee(
			String name, String email, 
			LocalDate dob) {
		this.name = name;
		this.email = email;
		this.dob = dob;
		// this.age = age;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Integer getAge() {
		
		return Period.between(this.dob, LocalDate.now()).getYears();
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "EmployeeModel [id=" + id 
				+ ", name=" + name 
				+ ", email=" + email 
				+ ", dob=" + dob 
				+ ", age=" + age
				+ "]";
	}
	
	
	
}
