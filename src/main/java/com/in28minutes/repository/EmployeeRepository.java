package com.in28minutes.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.entity.Employee;

@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	private EntityManager em;

	public Employee save(Employee employee) {
		if (employee.getId() == null) {
			this.em.persist(employee);
		} else {
			return this.em.merge(employee);
		}
		return employee;
	}

	public Employee findById(Long id) {
		return this.em.find(Employee.class, id);
	}

	public List<Employee> findAll() {
		return this.em.createQuery("Select e from Employee e", Employee.class).getResultList();
	}
}
