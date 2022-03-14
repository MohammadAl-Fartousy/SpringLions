package com.qa.lions.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class LionTest {

	
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Lion.class).usingGetClass().verify();
	}

	@Test
	public void getAndSetTest() {
		
		Lion lion = new Lion();

		duck.setId(1L);
		duck.setAge(4);
		duck.setGender("Male");
		duck.setHabitat("Lake");
		duck.setName("Ducky");

		// make sure after the setters, they actually set the values and are not null
		assertNotNull(duck.getAge());
		assertNotNull(duck.getId());
		assertNotNull(duck.getGender());
		assertNotNull(duck.getName());
		assertNotNull(duck.getHabitat());

		// make sure that when we use the getters, they get the correct value
		assertEquals(duck.getAge(), 4);
		assertEquals(duck.getId(), 1L);
		assertEquals(duck.getGender(), "Female");
		assertEquals(duck.getName(), "Ducky");
		assertEquals(duck.getHabitat(), "Lake");
	}

	@Test
	public void allArgsConstructor() {
		Duck duck = new Duck(1L, 3, "Jordan", "House pond", "Male");

		// make sure after the setters, they actually set the values and are not null
		assertNotNull(duck.getAge());
		assertNotNull(duck.getId());
		assertNotNull(duck.getGender());
		assertNotNull(duck.getName());
		assertNotNull(duck.getHabitat());

		// make sure that when we use the getters, they get the correct value
		assertEquals(duck.getAge(), 3);
		assertEquals(duck.getId(), 1L);
		assertEquals(duck.getGender(), "Male");
		assertEquals(duck.getName(), "Jordan");
		assertEquals(duck.getHabitat(), "House pond");
	}
}

