package com.qa.lions.service;
import java.util.List;

public interface ServiceMethods<L> {

	
	L create(L lion);
	
	
	List<L> readAll();
	

	L readById(long id);
	
	
	L update(long id, L duck);
	
	
	boolean delete(long id);
	
	
}
