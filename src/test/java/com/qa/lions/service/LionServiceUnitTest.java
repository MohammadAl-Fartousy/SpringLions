package com.qa.lions.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.lions.entity.Lion;
import com.qa.lions.repo.LionRepo;

@SpringBootTest
public class LionServiceUnitTest {


	@Autowired
	private LionService service;

	
	@MockBean
	private LionRepo repo;

	@Test
	public void createLionTest() {
		Lion input = new Lion(3, "Nathon", "Thick brush", "Male");
		Lion output = new Lion(1L, 3, "Nathon", "Thick brush", "Male");

		
		Mockito.when(this.repo.save(input)).thenReturn(output);


		assertEquals(output, this.service.create(input));
		
	
		Mockito.verify(this.repo, Mockito.times(1)).save(input);
	}
	
	@Test
	public void readByIdTest() {
		
		Optional<Lion> optionalOutput = Optional.of(new Lion(1L, 3, "Nathon", "Thick brush", "Male"));
		Lion output = new Lion(1L, 3, "Nathon", "Thick brush", "Male");
		
	
		Mockito.when(this.repo.findById(Mockito.anyLong())).thenReturn(optionalOutput);
		
		assertEquals(output, this.service.readById(Mockito.anyLong()));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyLong());
	}
	
	
	@Test
	public void deleteTrueTest() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		assertTrue(this.service.delete(1L));
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1l);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
		
	}
}