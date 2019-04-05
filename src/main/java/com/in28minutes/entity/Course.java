package com.in28minutes.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@NamedQueries(value = { @NamedQuery(name = "find_all_courses", query = "Select c From Course c") })
public class Course implements Serializable {

	private static final long serialVersionUID = -8795832437936109404L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@CreationTimestamp // automaticamento preenchido na criação da entidade
	@Column(updatable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp // automaticamento preenchido na atualização da entidade
	private LocalDateTime updatedDate;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY) // o owner será o review que terá a coluna course_id, por isso mappedBy course
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany // 1 curso pode ter n students.
	// definindo o nome da tabela associativa e as colunas
	@JoinTable(name = "course_student", joinColumns = {
			@JoinColumn(name = "course_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "student_id", referencedColumnName = "id") })
	@JsonIgnore
	private List<Student> students = new ArrayList<>();

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
}