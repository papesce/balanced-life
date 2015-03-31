package com.balance.life.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long taskId;
	
	
	private String name = "";
	
	@ManyToMany
	private List<Tag> tags;
	
	@Transient
	private String tagString;
	

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
	
    
    
    public String getTagString() {
    	return tagString;
    }
    
    
}
