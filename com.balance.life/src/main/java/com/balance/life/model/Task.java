package com.balance.life.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long taskId;
	
	
	private String name = "";
	
	@ManyToMany
	private List<Tag> tags;
	
	 
	

	protected Task() {
	}
	
	public Task(String name) {
		this.name= name;
	}
	 
    public long getTaskId() {
        return taskId;
    }

    public String getName() {
	        return name;
	}
    
    public List<Tag> getTags() {
		return tags;
	}
	
}
