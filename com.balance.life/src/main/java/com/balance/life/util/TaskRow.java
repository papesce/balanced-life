package com.balance.life.util;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.balance.life.model.Tag;
import com.balance.life.model.Task;

public class TaskRow {

	private long taskId;
	
	private String name = "";
	
	private List<Tag> tags;
	
	private String tagString = "";

	protected TaskRow() {
	}
	
	public TaskRow(String name) {
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

	public Task getTask() {
		Task task = new Task(this.name);
		task.setId(this.taskId);
		task.getTags().addAll(this.getTags());
		return task;
	}
    
   
    
}
