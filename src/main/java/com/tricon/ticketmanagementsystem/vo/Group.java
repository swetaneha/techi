package com.tricon.ticketmanagementsystem.vo;

import java.util.List;

public class Group {
	
	private int groupId;
	private Employee groupadmin;
	private List<Employee> groupmembers; 
	private String groupName;
	private String description;
	private String isArchived;
	
	public Group(Employee groupadmin) {
		this.groupadmin= groupadmin;
	}

	public Group(Employee groupadmin , List<Employee> groupmembers) {
		
		this.groupadmin= groupadmin;
		this.groupmembers= groupmembers;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getIsArchived() {
		return isArchived;
	}
	
	public void setIsArchived(String  isArchived) {
		
			this.isArchived = isArchived;
	}
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public List<Employee> getGroupmembers() {
		return groupmembers;
	}

	public void setGroupmembers(List<Employee> groupmembers) {
		this.groupmembers = groupmembers;
	}

	public Employee getGroupadmin() {
		return groupadmin;
	}

	public void setGroupadmin(Employee groupadmin) {
		this.groupadmin = groupadmin;
	}
	
}
