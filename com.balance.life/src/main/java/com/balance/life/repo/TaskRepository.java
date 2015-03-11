package com.balance.life.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balance.life.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	  
	
}
