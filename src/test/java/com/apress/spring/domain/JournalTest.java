package com.apress.spring.domain;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JournalTest {

	private Journal journal;

	@Before
	public void setUp() throws Exception {
		this.journal = new Journal("Get to know Spring Boot", "Today I will learn Spring Boot", "01/01/2017");
	}

	@After
	public void tearDown() throws Exception {
		this.journal = null;
	}

	@Test
	public void testGetCreatedAsShort() {
		assertEquals("01/01/2017", this.journal.getCreatedAsShort());
	}

	@Test
	public void testGetId() {
		assertNull(this.journal.getId());
	}

	@Test
	public void testSetId() {
		this.journal.setId(1L);
		assertEquals(new Long(1), this.journal.getId());
	}

	@Test
	public void testGetTitle() {
		assertEquals("Get to know Spring Boot", this.journal.getTitle());
	}

	@Test
	public void testSetTitle() {
		this.journal.setTitle("my new title");
		assertEquals("my new title", this.journal.getTitle());
	}

	@Test
	public void testGetCreated() {
		assertEquals(LocalDate.of(2017, 01, 01), this.journal.getCreated());
	}

	@Test
	public void testSetCreated() {
		this.journal.setCreated(LocalDate.of(2016, 01, 01));
		assertEquals(LocalDate.of(2016, 01, 01), this.journal.getCreated());
	}

	@Test
	public void testGetSummary() {
		assertEquals("Today I will learn Spring Boot", this.journal.getSummary());
	}

	@Test
	public void testSetSummary() {
		this.journal.setSummary("my new summary");
		assertEquals("my new summary", this.journal.getSummary());
	}

	@Test
	public void testToString() {
		assertEquals(
				"Journal(id=null, title=Get to know Spring Boot, created=2017-01-01, summary=Today I will learn Spring Boot)",
				this.journal.toString());
	}

}
