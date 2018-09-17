package com.tricon.ticketmanagementsystem.vo;

public class Status {
	
	int id;
	private String status;
	private String isArchived ;
	
	public String getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(String isArchived) {
		this.isArchived = isArchived;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}