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

		
		lion.setId(1L);
		lion.setAge(4);
		lion.setGender("Male");
		lion.setHabitat("Savanna");
		lion.setName("Mufasa");

		
		assertNotNull(lion.getAge());
		assertNotNull(lion.getId());
		assertNotNull(lion.getGender());
		assertNotNull(lion.getName());
		assertNotNull(lion.getHabitat());


		assertEquals(lion.getAge(), 4);
		assertEquals(lion.getId(), 1L);
		assertEquals(lion.getGender(), "Male");
		assertEquals(lion.getName(), "Mufasa");
		assertEquals(lion.getHabitat(), "Savanna");
	}

	@Test
	public void allArgsConstructor() {
		Lion lion = new Lion(1L, 3, "Nathon", "Thick brush", "Male");

		assertNotNull(lion.getAge());
		assertNotNull(lion.getId());
		assertNotNull(lion.getGender());
		assertNotNull(lion.getName());
		assertNotNull(lion.getHabitat());

		assertEquals(lion.getAge(), 3);
		assertEquals(lion.getId(), 1L);
		assertEquals(lion.getGender(), "Male");
		assertEquals(lion.getName(), "Nathon");
		assertEquals(lion.getHabitat(), "Thick brush");
	}
}