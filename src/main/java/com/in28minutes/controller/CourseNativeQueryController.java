package com.in28minutes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.entity.Course;
import com.in28minutes.repository.CourseNatvieQueryRepository;

@RestController
@RequestMapping("/courses/native-query")
public class CourseNativeQueryController {
	
	@Autowired
	private CourseNatvieQueryRepository repository;
	
	@GetMapping("/simple-query")
	public List<?> findAllSimpleQuery() {
		return this.repository.nativeQueryBasic();
	}

	@GetMapping("/typed-query")
	public List<Course> findAllTypedQuery() {
		return this.repository.nativeQueryTyped();
	}

	@GetMapping("/{name}")
	public List<Course> findByName(@PathVariable String name) {
		return this.repository.findByNameNativeQuery(name);
	}

	@GetMapping("/with-named-parameter/{name}")
	public List<Course> findByNameWithNamedParameter(@PathVariable String name) {
		return this.repository.findByNameNativeQueryWithNamedParameter(name);
	}
}