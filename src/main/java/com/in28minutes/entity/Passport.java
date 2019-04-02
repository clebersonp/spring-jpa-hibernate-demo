package com.in28minutes.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "passport")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Passport implements Serializable {

	private static final long serialVersionUID = -8795832437936109404L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String number;
	
	// mappedBy mantem o owner da coluna passport_id soment na entidade student
	//ou seja, a tabela student tera uma coluna de referencia do id do passport
	// porem o contrario nao será verdade
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
	// como o mapeamento esta sendo feito nas duas entidades,
	// preciso ignorar a serializacao do json em um dos lados
	@JsonIgnore
	private Student student;
}