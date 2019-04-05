package com.in28minutes.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.entity.Review;

@Repository
@Transactional
public class ReviewRepository {

	@Autowired
	private EntityManager em;

	public Review save(Review review) {
		if (review.getId() == null) {
			this.em.persist(review);
		} else {
			this.em.merge(review);
		}
		return review;
	}
}
