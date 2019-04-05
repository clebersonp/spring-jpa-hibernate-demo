package com.in28minutes.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import com.in28minutes.entity.Review;
import com.in28minutes.entity.Student;
import com.in28minutes.model.CourseDTO;
import com.in28minutes.model.ReviewDTO;
import com.in28minutes.repository.CourseRepository;
import com.in28minutes.repository.ReviewRepository;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@GetMapping("/{id}")
	public Course findById(@PathVariable Long id) {
		return this.courseRepository.findById(id);
	}

	@PostMapping
	public Course save(@RequestBody CourseDTO courseDto) throws IllegalAccessException, InvocationTargetException {
		return saveOrUpdate(courseDto);
	}

	@PostMapping("/{id}/review")
	public Course saveReview(@PathVariable(name = "id") final Long id, @RequestBody ReviewDTO reviewDto)
			throws IllegalAccessException, InvocationTargetException {

		Review review = new Review();
		BeanUtils.copyProperties(review, reviewDto);

		Course course = this.courseRepository.findById(id);

		course.addReview(review);
		review.setCourse(course);
		this.reviewRepository.save(review);

		// para atualizar a instancia de course com a nova review adicionada
		// this.courseRepository.refresh(course); // ou eu poderia adicionar o review a
		// lista de reviews do course

		return course;
	}

	@PutMapping
	public Course update(@RequestBody CourseDTO courseDto) throws IllegalAccessException, InvocationTargetException {
		return saveOrUpdate(courseDto);
	}

	private Course saveOrUpdate(CourseDTO courseDto) throws IllegalAccessException, InvocationTargetException {
		Course coursePersistence = new Course();
		BeanUtils.copyProperties(coursePersistence, courseDto);
		return this.courseRepository.save(coursePersistence);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable Long id) {
		this.courseRepository.deleteById(id);
	}

	@GetMapping
	public List<Course> retrieveAllCourses() {
		return this.courseRepository.findAll();
	}

	@GetMapping("/{course-id}/students")
	public List<Student> retrieveAllStudentsOfACourse(@PathVariable(name = "course-id") Long courseId) {
		Optional<Course> optionalCourse = Optional.ofNullable(this.courseRepository.findById(courseId));
		if (optionalCourse.isPresent()) {
			return optionalCourse.get().getStudents();
		}
		return Collections.emptyList();
	}
}