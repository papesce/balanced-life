package com.balance.life.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemStatusLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long itemStatusId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Item item;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Status status;
	
	private Date timestamp;

}
