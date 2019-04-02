package com.in28minutes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.entity.Course;
import com.in28minutes.repository.CourseJPQLRepository;

@RestController
@RequestMapping("/courses/jpql")
public class CourseJPQLController {
	
	@Autowired
	private CourseJPQLRepository repository;
	
	@GetMapping("/simple-query")
	public List<?> findAllSimpleQuery() {
		return this.repository.jpqlBasic();
	}

	@GetMapping("/typed-query")
	public List<Course> findAllTypedQuery() {
		return this.repository.jpqlTyped();
	}

	@GetMapping("/{name}")
	public List<Course> findByName(@PathVariable String name) {
		return this.repository.findByName(name);
	}
}