package com.qa.lions.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.lions.entity.Lion;
import com.qa.lions.repo.LionRepo;

@Service
public class LionService implements ServiceMethods<Lion>{
	
	private LionRepo repo;
	
	
	public LionService(LionRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Lion create(Lion lion) {
		return this.repo.save(lion);
	}

	@Override
	public List<Lion> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Lion readById(long id) {
		Optional<Lion> getLion = this.repo.findById(id);
		if(getLion.isPresent()) {
			return getLion.get();
		}
		return null;
	}


	@Override
	public Lion update(long id, Lion lion) {
		Optional<Lion> existingLion = this.repo.findById(id);
		if(existingLion.isPresent()) {
			Lion exists = existingLion.get();
			exists.setAge(lion.getAge());
			exists.setGender(lion.getGender());
			exists.setHabitat(lion.getHabitat());
			exists.setName(lion.getName());
			
			return this.repo.saveAndFlush(exists);
		}
		return null;
	} 

	
	@Override
	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	

}
