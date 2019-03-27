package com.in28minutes.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.SpringJpaHibernateDemoApplication;
import com.in28minutes.entity.Course;

/**
 * Tests usando config de test -> src/test/resources
 * @author cleberson
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringJpaHibernateDemoApplication.class })
@Sql(scripts = { "/test-data.sql" })
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository repository;
	
	@Test
	public void deve_encontrar_course_findById() {
		Course course = this.repository.findById(5L);
		assertEquals("Java 8", course.getName());
	}
	
	@Test
	public void nao_deve_encontrar_course_findById_para_id_invalido() {
		Course course = this.repository.findById(100000L);
		assertNull(course);
	}
	
	@Test
	@DirtiesContext // spring context reset the data changes of data base
	public void deve_remover_course_byId() {
		this.repository.deleteById(2L);
		Course course = this.repository.findById(2L);
		assertNull(course);
	}
	
	@Test
	public void deve_encontrar_novamente_course_byId_2_after_spring_resets() {
		assertNotNull(this.repository.findById(2L));
	}
	
	@Test
	@DirtiesContext
	public void deve_salvar_um_novo_course() {
		Course course = new Course();
		course.setName("New one");
		Course courseSaved = this.repository.save(course);
		assertThat(courseSaved.getId(), is(notNullValue()));
	}

	@Test
	@DirtiesContext
	public void deve_atualizar_um_course() {
		Course course1 = this.repository.findById(1L);
		course1.setName("Updated One");
		this.repository.save(course1);
		
		Course course2 = this.repository.findById(1L);
		assertThat(course2.getName(), is(equalTo("Updated One")));
	}
}
