package com.balance.life.util;

import java.util.Date;

import com.balance.life.model.Item;

public class ItemHistory extends ItemRow {

	private Date timestamp;
	
	public ItemHistory(Item item, Date date){
		setItem(item);
		this.timestamp = date;
	}

	

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
