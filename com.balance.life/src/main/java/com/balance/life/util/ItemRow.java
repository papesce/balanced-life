package com.balance.life.util;

import java.util.ArrayList;
import java.util.List;

import com.balance.life.model.Association;
import com.balance.life.model.Item;
import com.balance.life.model.Status;
import com.balance.life.model.Tag;


public class ItemRow {

	private long itemId;
	
	private String name = "";
	
	private List<Tag> tags = new ArrayList<Tag>();
	
	private List<Association> associations = new ArrayList<Association>();
	
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
    
	protected void setItem(Item item) {
		this.name = item.getName();
		this.itemId = item.getItemId();
		this.currentStatus = item.getCurrentStatus();
		this.getTags().addAll(item.getTags());
		this.getAssociations().addAll(item.getAssociations());
	}
    
}
