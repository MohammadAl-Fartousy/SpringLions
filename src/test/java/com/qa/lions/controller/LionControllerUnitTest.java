package com.qa.lions.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.lions.entity.Lion;
import com.qa.lions.service.LionService;

@SpringBootTest

public class LionControllerUnitTest {
	
	@Autowired
	private LionController controller;

	@MockBean
	private LionService service;

	@Test
	public void createLionTest() {
		Lion lion = new Lion(12, "Richard", "Disneyworld", "Male");

		Mockito.when(this.service.create(lion)).thenReturn(lion);

		ResponseEntity<Lion> response = new ResponseEntity<Lion>(lion, HttpStatus.CREATED);

		assertThat(response).isEqualTo(this.controller.createLion(lion));

		Mockito.verify(this.service, times(1)).create(lion);
	}
}
