package com.in28minutes.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO implements Serializable {
	
	private static final long serialVersionUID = 764677217538399970L;
	private Long id;
	private String rating;
	private String description;
}
