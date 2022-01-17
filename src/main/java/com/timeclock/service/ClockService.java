package com.timeclock.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeclock.model.ClockEntity;
import com.timeclock.repository.ClockRepository;


@Service
public class ClockService  {

	@Autowired
	private ClockRepository repo;
	
	public void saveEntry(ClockEntity clockRepository) {
		repo.save(clockRepository);
	}
	
	public java.util.List<ClockEntity> getAllData(){
		
		return repo.findAll();
	}
	
	public void updateById(ClockEntity entity) {
		
		System.out.println("Entry Time ==> "+entity.getId());

		  repo.save(entity);
		
	}
	
	public void deleteById(long id) {
		
		repo.deleteById(id);
		
	}

	
}
