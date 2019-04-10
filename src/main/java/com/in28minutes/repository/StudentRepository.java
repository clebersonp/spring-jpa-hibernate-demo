package com.in28minutes.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.entity.Course;
import com.in28minutes.entity.Passport;
import com.in28minutes.entity.Student;
import com.in28minutes.model.StudentDTO;

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
		return this.em.find(Student.class, id);
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

	public Student saveWithCourse(StudentDTO studentDTO) {
		Student newStudent = new Student();
		Course newCourse = new Course();
		BeanUtils.copyProperties(studentDTO, newStudent, "passport", "course");
		BeanUtils.copyProperties(studentDTO.getCourse(), newCourse);

		newStudent.addCourse(newCourse);
		newCourse.addStudent(newStudent);

		this.em.persist(newStudent);
		this.em.persist(newCourse);

//		this.em.flush(); ou no fim da transação será inserida na tabela intermediaria

		return newStudent;
	}
}
