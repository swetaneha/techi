package com.tricon.ticketmanagementsystem.vo;

public class WorkflowCreate {
	
	private String name;
	private String description;
	private int[] status;
	private int[] ranks;
	//private String isActive;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int[] getStatus() {
		return status;
	}
	public void setStatus(int[] status) {
		this.status = status;
	}
	public int[] getRanks() {
		return ranks;
	}
	public void setRanks(int[] ranks) {
		this.ranks = ranks;
	}

//	public String getIsActive() {
//		return isActive;
//	}
//	public void setIsActive(String isActive) {
//		this.isActive = isActive;
//	} 

}
