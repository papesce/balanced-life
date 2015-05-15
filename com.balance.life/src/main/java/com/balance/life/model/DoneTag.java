package com.balance.life.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DoneTag{

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long tagId = 0;
	
	private String name = "";


	
	protected DoneTag() {
	}
	
	
	public DoneTag(String name){
		this.name = name;
	}
	
	public long getTagId() {
		return tagId;
	}
	
	public String getName() {
		return name;
	}


	
}
