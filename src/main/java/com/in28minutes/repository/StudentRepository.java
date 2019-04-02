package com.in28minutes.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.entity.Passport;
import com.in28minutes.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@Autowired
	private EntityManager em;
	
	public Student saveWithPassport(Student student) {
		if (student.getId() == null) {
			// primeiro preciso persistir um passport pois com sua chave primaria eu consigo relacionar na tabela student
			Passport passport = student.getPassport();
			this.em.persist(passport);
			this.em.persist(student);
		} else {
			return this.em.merge(student);
		}
		return student;
	}
}
