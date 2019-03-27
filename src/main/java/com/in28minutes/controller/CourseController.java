package com.in28minutes.controller;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.entity.Course;
import com.in28minutes.model.CourseDTO;
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
	public Course save(@RequestBody CourseDTO courseDto) throws IllegalAccessException, InvocationTargetException {
		return saveOrUpdate(courseDto);
	}
	
	@PutMapping
	public Course update(@RequestBody CourseDTO courseDto) throws IllegalAccessException, InvocationTargetException {
		return saveOrUpdate(courseDto);
	}

	private Course saveOrUpdate(CourseDTO courseDto) throws IllegalAccessException, InvocationTargetException {
		Course coursePersistence = new Course();
		BeanUtils.copyProperties(coursePersistence, courseDto);
		return this.repository.save(coursePersistence);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable Long id) {
		this.repository.deleteById(id);
	}
}