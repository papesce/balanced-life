package com.balance.life.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AssociationMetadata {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long assocMetadataId;
	
	private String name;

	public long getAssocMetadataId() {
		return assocMetadataId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
