package com.in28minutes.repository;

import java.util.List;
import java.util.Optional;

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
			// primeiro preciso persistir um passport pois com sua chave primaria eu consigo
			// relacionar na tabela student
			Passport passport = student.getPassport();
			this.em.persist(passport);
			this.em.persist(student);
		} else {
			return this.em.merge(student);
		}
		return student;
	}

	public Student findById(Long id) {
		Student find = this.em.find(Student.class, id);
		return find;
	}

	public Student findStudentByPassportId(Long id) {
		Optional<Passport> optionalPassport = Optional.ofNullable(this.em.find(Passport.class, id));
		if (optionalPassport.isPresent()) {
			return optionalPassport.get().getStudent();
		}
		return null;
	}

	public List<Student> findAll() {
		return this.em.createNamedQuery("find_all_students", Student.class).getResultList();
	}
}
