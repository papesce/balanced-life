package com.balance.life.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Goal {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long goalId;
	
	
	private String name = "";
	

	protected Goal() {
	}
	
	public Goal(String name) {
		this.name= name;
	}
	 
    public long getGoalId() {
        return goalId;
    }

    public String getName() {
	        return name;
	}
    

	public void setId(long goalId) {
		this.goalId = goalId;
		
	}

	
    
    
   
    
}
