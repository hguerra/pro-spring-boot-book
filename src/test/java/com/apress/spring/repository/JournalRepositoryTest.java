package com.apress.spring.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.apress.spring.domain.Journal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JournalRepositoryTest {

	private static final int TEST_SIZE_JOURNAL = 4;
	
	@Autowired
	private JournalRepository repository;

	@Test
	public void testFindAll() {
		this.repository.save(new Journal("Get to know Spring Boot", "Today I will learn Spring Boot", "01/01/2016"));
		this.repository.save(new Journal("Simple Spring Boot Project", "I will do my first Spring Boot Project", "01/02/2016"));
		this.repository.save(new Journal("Spring Boot Reading", "Read more about Spring Boot", "02/01/2016"));
		this.repository.save(new Journal("Spring Boot in the Cloud", "Spring Boot using Cloud Foundry", "03/01/2016"));
		
		List<Journal> jounals = this.repository.findAll();
		assertEquals(TEST_SIZE_JOURNAL, jounals.size());
		assertNotNull(jounals.get(0).getId());
		assertNotNull(jounals.get(1).getId());
		assertNotNull(jounals.get(2).getId());
		assertNotNull(jounals.get(3).getId());
	}
}
