package com.in28minutes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.entity.Course;
import com.in28minutes.repository.CourseRepository;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseRepository repository;
	
	@GetMapping("/{id}")
	public Course findById(@PathVariable Long id) {
		return this.repository.findById(id);
	}
	
	@PostMapping
	public Course save(@RequestBody Course course) {
		return this.repository.save(course);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable Long id) {
		this.repository.deleteById(id);
	}
}