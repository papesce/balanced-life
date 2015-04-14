package com.balance.life.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class DoneTask {

	private Date timestamp;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long taskId;
	
	
	private String name = "";
	
	@ManyToMany
	private List<DoneTag> tags = new ArrayList<DoneTag>();
	

	protected DoneTask() {
	}
	
	public DoneTask(String name) {
		this.name= name;
	}
	 
    public long getTaskId() {
        return taskId;
    }

    public String getName() {
	        return name;
	}
    
    public List<DoneTag> getTags() {
		return tags;
	}

	public void setId(long taskId) {
		this.taskId = taskId;
		
	}

	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
}
