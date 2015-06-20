package com.balance.life.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balance.life.model.AssociationMetadata;


public interface AssociationMetadataRepository extends JpaRepository<AssociationMetadata, Long>{
	
	
	AssociationMetadata findByName(String name);

}
