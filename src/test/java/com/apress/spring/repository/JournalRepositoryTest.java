package com.apress.spring.repository;

import static org.junit.Assert.assertEquals;

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

	@Autowired
	private JournalRepository repository;

	@Test
	public void testFindAll() {
		List<Journal> jounals = this.repository.findAll();
		assertEquals(4, jounals.size());
	}

}
