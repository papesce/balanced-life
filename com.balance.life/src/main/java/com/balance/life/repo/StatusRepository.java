package com.balance.life.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balance.life.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{ 
	
	Status findByName(String name);

}
