package com.balance.life.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	
	private String name = "";

	 
	protected Task() {
	}
	
	public Task(String name) {
		this.name= name;
	}
	 
    public long getId() {
        return id;
    }

    public String getName() {
	        return name;
	}
	
}
