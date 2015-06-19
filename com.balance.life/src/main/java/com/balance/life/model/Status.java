package com.balance.life.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long statusId;
	
	private String name = IDefaultStatus.CREATED; //completed, started, paused
	
	
	protected Status() {
	}
	
	public long getStatusId(){
		return statusId;
	}
	
	public Status(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
