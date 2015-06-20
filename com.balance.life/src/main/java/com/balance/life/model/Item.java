package com.balance.life.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long itemId;
	
	
	private String name = "";
	
	private Date creationDate = Calendar.getInstance().getTime();
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Status currentStatus;
	
	
	@ManyToMany
	private List<Tag> tags = new ArrayList<Tag>();
	
	@ManyToMany
	private List<Association> associations = new ArrayList<Association>();
	
	

	protected Item() {
	}
	
	public Item(String name) {
		this.name= name;
		this.currentStatus = new Status();
	}
	 
    public long getItemId() {
        return itemId;
    }

    public String getName() {
	        return name;
	}
    
    public List<Tag> getTags() {
		return tags;
	}

    public List<Association> getAssociations() {
		return associations;
	}

    
	public void setId(long itemId) {
		this.itemId = itemId;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
		
	}

	
    
    
   
    
}
