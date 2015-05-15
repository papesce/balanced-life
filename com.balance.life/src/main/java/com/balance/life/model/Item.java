package com.balance.life.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long itemId;
	
	
	private String name = "";
	
	@ManyToMany
	private List<Tag> tags = new ArrayList<Tag>();
	
	@ManyToMany
	private List<Association> association = new ArrayList<Association>();
	
	

	protected Item() {
	}
	
	public Item(String name) {
		this.name= name;
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

	public void setId(long itemId) {
		this.itemId = itemId;
		
	}

	
    
    
   
    
}
