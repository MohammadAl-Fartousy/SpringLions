package com.qa.lions.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.lions.entity.Lion;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:lion-schema.sql",
		"classpath:lion-data.sql" })
public class LionControllerIntegrationtest {

	@Autowired 
	private MockMvc mvc; 

	@Autowired
	private ObjectMapper mapper; 

	@Test
	public void test() {
		assertEquals(2, 1 + 1);
	}

	@Test
	public void testCreate() throws Exception {
	
		Lion testLion = new Lion(12, "Richard", "Disneyworld", "Male");
		String testLionAsJSON = this.mapper.writeValueAsString(testLion);
		RequestBuilder req = post("/lion/create").content(testLionAsJSON).contentType(MediaType.APPLICATION_JSON);

		Lion testSavedLion = new Lion(2, 12, "Richard", "Disneyworld", "Male");
		String testSavedLionAsJSON = this.mapper.writeValueAsString(testSavedLion);
	
		ResultMatcher checkStatus = status().isCreated();
	
		ResultMatcher checkBody = content().json(testSavedLionAsJSON);

		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	public void testCreate2() throws Exception {
		
		Lion testLion = new Lion(9, "Donald", "Zoo", "Male");
		String testLionAsJSON = this.mapper.writeValueAsString(testLion);
		RequestBuilder req = post("/lion/create").content(testLionAsJSON).contentType(MediaType.APPLICATION_JSON);

		Lion testSavedLion = new Lion(2, 15, "Daffy", "Zoo", "Male");
		String testSavedDuckAsJSON = this.mapper.writeValueAsString(testSavedLion);
		
		ResultMatcher checkStatus = status().isCreated();
	
		ResultMatcher checkBody = content().json(testSavedLionAsJSON);

		// run the request and check both matchers
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	public void testReadById() throws Exception {
		RequestBuilder req = get("/duck/readById/1");

		ResultMatcher checkStatus = status().isOk();

		Duck savedDuck = new Duck(1, 15, "Duck Dodgers", "space", "male");
		String savedDuckAsJSON = this.mapper.writeValueAsString(savedDuck);

		ResultMatcher checkBody = content().json(savedDuckAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testReadAll() throws Exception {
		Duck entry = new Duck(1L, 15, "Duck Dodgers", "space", "male");
		List<Duck> ducks = new ArrayList<>();
		ducks.add(entry);
		String ducksOutputAsJson = this.mapper.writeValueAsString(ducks);
		
		this.mvc.perform(get("/duck/readAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(ducksOutputAsJson));
	}
}