package com.balance.life.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balance.life.model.Association;


public interface AssociationRepository extends JpaRepository<Association, Long>{ 

	
	public final static String FIND_BY_SOURCE_AND_NAME_QUERY = 
			 "SELECT a " + 
             "FROM Association a LEFT JOIN a.assocMetadata m LEFT JOIN a.source s " +
             "WHERE m.name = :name and s.itemId = :sourceId";

	
	@Query(FIND_BY_SOURCE_AND_NAME_QUERY)
	Association findBySourceAndName(@Param("sourceId") long sourceId, @Param("name") String name);
	
	
	
	List<Association> findAllByTargetItemId(long targetId);
	
	
}
