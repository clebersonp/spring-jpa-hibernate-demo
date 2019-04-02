package com.in28minutes.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@NamedQueries(value = { 
		@NamedQuery(name = "find_all_courses", query = "Select c From Course c")
})
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
}