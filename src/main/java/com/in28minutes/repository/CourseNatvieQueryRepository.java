package com.in28minutes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.entity.Course;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class CourseNatvieQueryRepository {

	@Autowired
	private EntityManager em;
	
	public List<?> nativeQueryBasic() {
		Query simpleQuery = this.em.createNativeQuery("select * from course");
		return simpleQuery.getResultList();
	}

	public List<Course> nativeQueryTyped() {
		Query nativeQuery = this.em.createNativeQuery("select * from course", Course.class);
		return nativeQuery.getResultList();
	}

	public List<Course> findByNameNativeQuery(String name) {
		Query nativeQuery = this.em.createNativeQuery("select * from course where lower(name) like lower(?)", Course.class);
		nativeQuery.setParameter(1, "%" + name + "%");
		return nativeQuery.getResultList();
	}

	public List<Course> findByNameNativeQueryWithNamedParameter(String name) {
		Query nativeQuery = this.em.createNativeQuery("select * from course where lower(name) like lower(:name)", Course.class);
		nativeQuery.setParameter("name", "%" + name + "%");
		return nativeQuery.getResultList();
	}
}
