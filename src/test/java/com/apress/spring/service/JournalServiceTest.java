package com.apress.spring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.apress.spring.domain.Journal;
import com.apress.spring.repository.JournalRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JournalServiceTest {

	private static final Long TEST_ID = 1L;
	private static final int TEST_SIZE_JOURNAL = 4;

	@Autowired
	private JournalService service;

	@MockBean
	private JournalRepository repositoryMock;

	private Journal journal;

	@Before
	public void setUp() throws Exception {
		List<Journal> journals = Arrays.asList(
				new Journal("Get to know Spring Boot", "Today I will learn Spring Boot", "01/01/2016"),
				new Journal("Simple Spring Boot Project", "I will do my first Spring Boot Project", "01/02/2016"),
				new Journal("Spring Boot Reading", "Read more about Spring Boot", "02/01/2016"),
				new Journal("Spring Boot in the Cloud", "Spring Boot using Cloud Foundry", "03/01/2016"));

		given(this.repositoryMock.findAll()).
		willReturn(journals);
		
		this.journal = journals.get(0);
		Journal journalMock = new Journal(this.journal.getTitle(), this.journal.getSummary(), this.journal.getCreatedAsShort());
		journalMock.setId(TEST_ID);

		given(this.repositoryMock.save(this.journal)).
		willReturn(journalMock);
	}

	@After
	public void tearDown() throws Exception {
		this.journal = null;
	}

	@Test
	public void shouldNotBeNullRepositoryMock() {
		assertNotNull(this.repositoryMock);
	}

	@Test
	public void shouldNotBeNullService() {
		assertNotNull(this.service);
	}

	@Test
	public void shouldSaveANewJournal() {
		Journal savedJournal = this.service.save(this.journal);

		assertNull(this.journal.getId());
		assertNotNull(savedJournal);
		assertNotNull(savedJournal.getId());
		assertEquals(this.journal.getTitle(), savedJournal.getTitle());
		assertEquals(this.journal.getSummary(), savedJournal.getSummary());
		assertEquals(this.journal.getCreated(), savedJournal.getCreated());
		assertEquals(this.journal.getCreatedAsShort(), savedJournal.getCreatedAsShort());
	}

	@Test
	public void testFindAll() {
		List<Journal> journals = this.service.findAll();
		
		assertNotNull(journals);
		assertEquals(TEST_SIZE_JOURNAL, journals.size());
		
		Journal firstJournal = journals.get(0);
		assertEquals(this.journal.getTitle(), firstJournal.getTitle());
		assertEquals(this.journal.getSummary(), firstJournal.getSummary());
		assertEquals(this.journal.getCreated(), firstJournal.getCreated());
		assertEquals(this.journal.getCreatedAsShort(), firstJournal.getCreatedAsShort());
	}
}
