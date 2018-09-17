package com.tricon.ticketmanagementsystem.dao;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tricon.ticketmanagementsystem.vo.*;

public interface IAdminDao {
	
	public List<Employee> getAllEmployees();
	public List<Group> getGroups(int count,int total);
	public String addGroup(Group obj);
	public Group getGroup(int id);
	public String updateGroup(Group obj , int id);
	public List<Employee> getEmployee(int id);
	public String  deleteGroup(int id);
	int countGroups();
	
}
