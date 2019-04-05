package com.in28minutes.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.entity.Passport;
import com.in28minutes.entity.Student;
import com.in28minutes.model.StudentDTO;
import com.in28minutes.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository repository;

	@PostMapping
	public Student save(@RequestBody StudentDTO studentDto) throws IllegalAccessException, InvocationTargetException {
		Student studentPersistence = new Student();
		Passport passportPersistence = new Passport();
		BeanUtils.copyProperties(studentDto, studentPersistence, "passport");
		BeanUtilsBean.getInstance().copyProperties(passportPersistence, studentDto.getPassport());

		studentPersistence.setPassport(passportPersistence);

		return this.repository.saveWithPassport(studentPersistence);
	}

	@GetMapping("/{id}")
	public Student findById(@PathVariable Long id) {
		Student findById = this.repository.findById(id);
		return findById;
	}

	@GetMapping("/retrieve-student-by-passport-id/{id}")
	public Student findStudentByPassportId(@PathVariable Long id) {
		Student findById = this.repository.findStudentByPassportId(id);
		return findById;
	}

	@GetMapping
	public List<Student> retrieveAllStudents() {
		return this.repository.findAll();
	}

}