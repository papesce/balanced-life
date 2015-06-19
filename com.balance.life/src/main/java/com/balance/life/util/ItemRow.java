package com.balance.life.util;

import java.util.List;

import com.balance.life.model.Item;
import com.balance.life.model.Status;
import com.balance.life.model.Tag;


public class ItemRow {

	private long itemId;
	
	private String name = "";
	
	private List<Tag> tags;
	
	private Status currentStatus;
	
	private String tagString = "";
	
	

	protected ItemRow() {
	}
	
	public ItemRow(String name) {
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
	
    public String getTagString() {
    	return tagString;
    }
    
    public Status getCurrentStatus() {
    	return currentStatus;
    }

	public Item getTask() {
		Item task = new Item(this.name);
		task.setId(this.itemId);
		task.setCurrentStatus(this.getCurrentStatus());
		task.getTags().addAll(this.getTags());
		return task;
	}
    
   
    
}
