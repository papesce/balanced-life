package com.balance.life.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Association {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long assocId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Item source;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Item target;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private AssociationMetadata assocMetadata;

}
