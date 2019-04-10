package com.in28minutes.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
// InheritanceType.SINGLE_TABLE) todos os filhos de employee serão persistidos na mesma tabela employee
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // será criada uma tabela para cadas classe concreta
@Inheritance(strategy = InheritanceType.JOINED) // serão criadas tabelas para a classe abstrata e para cada classe concreta, e
												// será feito join das tabelas concretas com a abstrata atraves da FK
//@DiscriminatorColumn(name = "employee_type") // para especificar o nome da coluna da herança com a strategy single_table
//@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public abstract class Employee implements Serializable {

	private static final long serialVersionUID = -8795832437936109404L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String name;

	public Employee(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}