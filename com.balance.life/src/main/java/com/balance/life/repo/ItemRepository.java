package com.balance.life.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balance.life.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	List<Item> findAllByTagsTagId(long tagId);

	  
	
}
