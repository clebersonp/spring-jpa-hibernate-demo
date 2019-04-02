package com.in28minutes.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassportDTO implements Serializable {

	private static final long serialVersionUID = -6055102466000531435L;
	private Long id;
	private String number;
}
