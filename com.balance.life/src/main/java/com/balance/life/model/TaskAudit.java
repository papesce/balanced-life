package com.balance.life.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//not used yet (support for undo)
@Entity
public class TaskAudit {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long taskAuditId;
	
	private String operation;
	private Date timestamp;
	private long taskId;
	
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public long getTaskAuditId() {
		return taskAuditId;
	}
}
