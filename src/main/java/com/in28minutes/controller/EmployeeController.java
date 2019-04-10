package com.in28minutes.controller;

import java.util.List;

import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.entity.Employee;
import com.in28minutes.entity.FullTimeEmployee;
import com.in28minutes.entity.PartTimeEmployee;
import com.in28minutes.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;

	@PostMapping("/fulltime")
	public Employee saveFullTime(@RequestBody FullTimeEmployee employee) {
		return this.repository.save(employee);
	}

	@PostMapping("/parttime")
	public Employee savePartTime(@RequestBody PartTimeEmployee employee) {
		return this.repository.save(employee);
	}

	@GetMapping("/{id}")
	public Employee findById(@PathVariable Long id) {
		return this.repository.findById(id);
	}

	@GetMapping
	public List<Employee> retrieveAllEmployees() {
		return this.repository.findAll();
	}

	@GetMapping("/mapped-super-class/retrieve-all-part-time")
	public List<PartTimeEmployee> retrieveAllPartTimeEmployees() {
		return this.repository.findAllPartTime();
	}

	@GetMapping("/mapped-super-class/retrieve-all-full-time")
	public List<FullTimeEmployee> retrieveAllFullTimeEmployees() {
		return this.repository.findAllFullTime();
	}
}