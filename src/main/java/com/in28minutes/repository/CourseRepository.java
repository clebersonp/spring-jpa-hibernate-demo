package com.in28minutes.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	@Autowired
	private EntityManager em;
	
	// public Course findById(Long id) -> insert or update
	public Course findById(Long id) {
		return this.em.find(Course.class, id);
	}
	
	// public Course save(Course course)
	public Course save(Course course) {
		return this.em.merge(course);
	}
	
	// public void deleteById(Long id)
	public void deleteById(Long id) {
		Optional<Course> optionalCourse = Optional.ofNullable(this.findById(id));
		if (optionalCourse.isPresent()) {
			this.em.remove(optionalCourse.get());
		}
	}
}
