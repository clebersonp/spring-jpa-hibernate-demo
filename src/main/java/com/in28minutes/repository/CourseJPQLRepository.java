package com.in28minutes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.entity.Course;

@Repository
@Transactional
public class CourseJPQLRepository {

	@Autowired
	private EntityManager em;
	
	public List<?> jpqlBasic() {
		Query simpleQuery = this.em.createNamedQuery("find_all_courses");
		return simpleQuery.getResultList();
	}

	public List<Course> jpqlTyped() {
		TypedQuery<Course> typedQuery = this.em.createNamedQuery("find_all_courses", Course.class);
		return typedQuery.getResultList();
	}

	public List<Course> findByName(String name) {
		TypedQuery<Course> typedQuery = this.em.createQuery("Select c From Course c where lower(c.name) like lower(:name)", Course.class);
		typedQuery.setParameter("name", "%" + name + "%");
		return typedQuery.getResultList();
	}
}
