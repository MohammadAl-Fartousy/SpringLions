package com.qa.lions.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.lions.entity.Lion;
import com.qa.lions.service.LionService;

@RestController
@RequestMapping("/lion")
public class LionController {
	
	private LionService service;
	
	@Autowired 
	public LionController(LionService service) {
		this.service = service;
	}

	
	@PostMapping("/create")
	public ResponseEntity<Lion> createLion(@RequestBody Lion lion){
		return new ResponseEntity<Lion>(this.service.create(lion), HttpStatus.CREATED);
	
	}
	

	@GetMapping("/readAll")
	public ResponseEntity<List<Lion>> readAllLions(){
		return new ResponseEntity<List<Lion>>(this.service.readAll(), HttpStatus.OK); 
	}
	
	
	
	@GetMapping("/readById/{id}")
	public ResponseEntity<Lion> readById(@PathVariable long id){
	return new ResponseEntity<Lion>(this.service.readById(id), HttpStatus.OK);
	
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Lion> updateLion(@PathVariable long id, @RequestBody Lion lion){
		return new ResponseEntity<Lion>(this.service.update(id, lion), HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deletelion(@PathVariable long id){
		return (this.service.delete(id)) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT) :
			new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		
	}
}