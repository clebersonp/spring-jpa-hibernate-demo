package com.in28minutes.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO implements Serializable {

	private static final long serialVersionUID = 2214930765826606631L;

	private Long id;
	private String name;
	@JsonProperty(value = "passport")
	private PassportDTO passport;
}
