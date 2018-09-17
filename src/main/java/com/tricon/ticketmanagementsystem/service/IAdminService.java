package com.tricon.ticketmanagementsystem.service;

import java.util.List;

import com.tricon.ticketmanagementsystem.vo.*;

public interface IAdminService {
	
	public List<Employee> getAllEmployees();
	public List<Group> getGroups(int page,int total);
	public String addGroup(Group obj);
	public Group getGroup(int id);
	public String updateGroup(Group obj , int id);
	public List<Employee> getEmployee(int id);
	public String  deleteGroup(int id);
	public int countGroups();
}
