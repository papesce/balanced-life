package com.balance.life.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balance.life.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {


	Tag findByName(String name);
	
}
