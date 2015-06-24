package com.balance.life.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.balance.life.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	String FIND_ALL_WITH_NO_PARENT_QUERY = "SELECT i FROM Item i "
			+ "WHERE  i.associations IS EMPTY";


	List<Item> findAllByTagsTagId(long tagId);


	@Query(FIND_ALL_WITH_NO_PARENT_QUERY)
	List<Item> findAllWithNoParent();

}
