package com.in28minutes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.entity.Employee;
import com.in28minutes.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;

	@PostMapping
	public Employee save(@RequestBody Employee employee) {
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
}