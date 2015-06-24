package com.balance.life.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Association {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long assocId;
	
	@JsonBackReference
	@ManyToOne//(fetch = FetchType.LAZY)
	private Item source;
	
	@ManyToOne
	private Item target;
	
	@ManyToOne
	private AssociationMetadata assocMetadata;
	
	public long getASsocId() {
		return assocId;
	}
	
	public Item getSource() {
		return source;
	}
	
	public Item getTarget() {
		return target;
	}
	
	public AssociationMetadata getAssociationMetadata() {
		return assocMetadata;
	}

	public void setTarget(Item targetItem) {
		this.target = targetItem;
		
	}

	public void setAssociationMetadata(AssociationMetadata assocMetadata) {
		this.assocMetadata = assocMetadata;
		
	}

	public void setSource(Item item) {
		this.source = item;
		
	}

	

}
