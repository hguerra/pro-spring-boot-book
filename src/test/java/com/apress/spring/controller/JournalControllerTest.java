package com.apress.spring.controller;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.apress.spring.domain.Journal;
import com.apress.spring.service.JournalService;
import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JournalControllerTest {
	
	private static final int TEST_SIZE_JOURNAL = 4;

	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private JournalService serviceMock;
	
	@Before
	public void setUp() {
		Journal journal1 = new Journal("Get to know Spring Boot", "Today I will learn Spring Boot", "01/01/2016");
		journal1.setId(1L);
		
		Journal journal2 = new Journal("Simple Spring Boot Project", "I will do my first Spring Boot Project", "01/02/2016");
		journal2.setId(2L);
		
		Journal journal3 = new Journal("Spring Boot Reading", "Read more about Spring Boot", "02/01/2016");
		journal3.setId(4L);
		
		Journal journal4 = new Journal("Spring Boot in the Cloud", "Spring Boot using Cloud Foundry", "03/01/2016");
		journal4.setId(5L);
		
		given(this.serviceMock.findAll()).
		willReturn(Arrays.asList(journal1, journal2, journal3, journal4));
	}
	
	@Test
	public void shouldLoadTheIndexPage() {		
		given().
			webAppContextSetup(context).
		when().
			get("/").
		then().
			statusCode(200);
	}

	@Test
	public void shouldReturnAllJournals() {
		JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().
				setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();
		
		given().
			webAppContextSetup(context).
		when().
			get("/journal").
		then().
			statusCode(200).
			contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
			assertThat().body(matchesJsonSchemaInClasspath("jounal-schema.json").using(jsonSchemaFactory)).
			body("size", equalTo(TEST_SIZE_JOURNAL)).
			body("title", hasItems("Get to know Spring Boot", "Simple Spring Boot Project", "Spring Boot Reading", "Spring Boot in the Cloud")).
			body("summary", hasItems("Today I will learn Spring Boot", "I will do my first Spring Boot Project", "Read more about Spring Boot", "Spring Boot using Cloud Foundry")).
			body("createdAsShort", hasItems("01/01/2016", "01/02/2016", "02/01/2016", "03/01/2016"));
	}

}
