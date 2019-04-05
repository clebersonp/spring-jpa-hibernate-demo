package com.in28minutes.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Student implements Serializable {

	private static final long serialVersionUID = -8795832437936109404L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToOne(fetch = FetchType.LAZY) // a entidade student é a owner no relacionamento com passport
	private Passport passport;

	// Mappedby é para dizer que o lado do course que tem a propriedade students
	// será o owner do relacionamento, caso contrario, serão criadas 2 tabelas
	// associativas para os dois lados
	@ManyToMany(mappedBy = "students") // 1 student pode estar em n courses
	private List<Course> courses = new ArrayList<>();

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public void removeCourse(Course course) {
		this.courses.remove(course);
	}
}