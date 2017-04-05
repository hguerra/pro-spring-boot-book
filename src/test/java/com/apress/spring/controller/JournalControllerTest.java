package com.apress.spring.controller;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JournalControllerTest {

	@Autowired
	private WebApplicationContext context;

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
			assertThat().
			body(matchesJsonSchemaInClasspath("jounal-schema.json").using(jsonSchemaFactory)).
			body("size", equalTo(4)).
			body("title", hasItems("Get to know Spring Boot", "Simple Spring Boot Project", "Spring Boot Reading", "Spring Boot in the Cloud")).
			body("summary", hasItems("Today I will learn Spring Boot", "I will do my first Spring Boot Project", "Read more about Spring Boot", "Spring Boot using Cloud Foundry")).
			body("createdAsShort", hasItems("01/01/2016", "01/02/2016", "02/01/2016", "03/01/2016"));
	}

}
