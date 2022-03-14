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
		
		Lion testLion = new Lion(10, "Donald", "Zoo", "Male");
		String testLionAsJSON = this.mapper.writeValueAsString(testLion);
		RequestBuilder req = post("/lion/create").content(testLionAsJSON).contentType(MediaType.APPLICATION_JSON);

		Lion testSavedLion = new Lion(2, 10, "Donald", "Zoo", "Male");
		String testSavedLionAsJSON = this.mapper.writeValueAsString(testSavedLion);
		
		ResultMatcher checkStatus = status().isCreated();
	
		ResultMatcher checkBody = content().json(testSavedLionAsJSON);

	
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	public void testReadById() throws Exception {
		RequestBuilder req = get("/lion/readById/1");

		ResultMatcher checkStatus = status().isOk();

		Lion savedLion = new Lion(1, 15, "Lion Alex", "space", "male");
		String savedLionAsJSON = this.mapper.writeValueAsString(savedLion);

		ResultMatcher checkBody = content().json(savedLionAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testReadAll() throws Exception {
		Lion entry = new Lion(1L, 15, "Lion Alex", "space", "male");
		List<Lion> lions = new ArrayList<>();
		lions.add(entry);
		String lionsOutputAsJson = this.mapper.writeValueAsString(lions);
		
		this.mvc.perform(get("/lion/readAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(lionsOutputAsJson));
	}
}