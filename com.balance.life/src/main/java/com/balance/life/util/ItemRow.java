package com.balance.life.util;

import java.util.List;

import com.balance.life.model.Association;
import com.balance.life.model.Item;
import com.balance.life.model.Status;
import com.balance.life.model.Tag;


public class ItemRow {

	private long itemId;
	
	private String name = "";
	
	private List<Tag> tags;
	
	private List<Association> associations;
	
	private Status currentStatus;
	
	private String tagString = "";
	
	private String associationString = "";
	


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
    
	public List<Association> getAssociations() {
		return associations;
	}

    
	public String getAssociationString() {
		return associationString;
	}

	
    public String getTagString() {
    	return tagString;
    }
    
    public Status getCurrentStatus() {
    	return currentStatus;
    }

	public Item getItem() {
		Item item = new Item(this.name);
		item.setId(this.itemId);
		item.setCurrentStatus(this.getCurrentStatus());
		item.getTags().addAll(this.getTags());
		item.getAssociations().addAll(this.getAssociations());
		return item;
	}
    
   
    
}
