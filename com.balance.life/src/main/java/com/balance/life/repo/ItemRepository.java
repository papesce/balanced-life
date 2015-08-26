package com.balance.life.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.balance.life.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{


	List<Item> findAllByTagsTagId(long tagId);


	@Query("SELECT i FROM Item i JOIN i.currentStatus s WHERE  s.name != 'DONE'"
			+ "AND  i.associations IS EMPTY")
	List<Item> findAllWithNoParent();


	@Query("SELECT i FROM Item i JOIN i.currentStatus s WHERE  s.name != 'DONE'")
	List<Item> findAllItems();


	List<Item> findAllItemsByTagsTagId(Long tagId);

	@Query("SELECT i FROM Item i JOIN i.currentStatus s WHERE  s.name = 'DONE'")
	List<Item> getHistory();

}
