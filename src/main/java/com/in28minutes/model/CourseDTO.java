package com.in28minutes.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseDTO implements Serializable {

	private static final long serialVersionUID = 1084859479138247471L;
	private Long id;
	private String name;
}