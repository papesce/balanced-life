package com.balance.gym.model;

import org.springframework.data.annotation.Id;

public class Excercise {
	
	@Id
    private String id;
	
	private String name;

	public Excercise(String name) {	
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
