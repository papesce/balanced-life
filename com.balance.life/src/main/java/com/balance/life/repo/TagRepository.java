package com.balance.life.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balance.life.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {


	Tag findByName(String name);
	
	//not used
	List<Tag> findAllByName(String name);
	
	List<Tag> findAllByName(List<String> name);
	
}
