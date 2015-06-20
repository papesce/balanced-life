package com.balance.life.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balance.life.model.Association;


public interface AssociationRepository extends JpaRepository<Association, Long>{ 

	
	public final static String FIND_BY_ASSOCIATION_METADATA_QUERY = 
			 "SELECT a " + 
             "FROM Association a LEFT JOIN a.assocMetadata b " +
             "WHERE b.name = :name";

	
	@Query(FIND_BY_ASSOCIATION_METADATA_QUERY)
	Association findByMetadataName(@Param("name") String name);
	
}
