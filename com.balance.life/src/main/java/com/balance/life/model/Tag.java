package com.balance.life.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long tagId = 0;
	
	private String name = "";

	
	protected Tag() {
	}
	
	
	public Tag(String name){
		this.name = name;
	}
	
	public long getTagId() {
		return tagId;
	}
	
	public String getName() {
		return name;
	}
}
