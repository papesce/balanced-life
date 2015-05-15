package com.balance.life.util;

import java.util.List;

import com.balance.life.model.Item;
import com.balance.life.model.Tag;


public class ItemTreeRow {

	private long itemId;
	
	private long parent = 0;
	
	private String name = "";
	
	private List<Tag> tags;
	
	private String tagString = "";

	protected ItemTreeRow() {
	}
	
	public ItemTreeRow(Item item) {
		this.name= item.getName();
		this.tags= item.getTags();
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

	public Item getTask() {
		Item task = new Item(this.name);
		task.setId(this.itemId);
		task.getTags().addAll(this.getTags());
		return task;
	}

	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}
    
   
    
}
