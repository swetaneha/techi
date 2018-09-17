package com.tricon.ticketmanagementsystem.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricon.ticketmanagementsystem.daoimpl.AdminDao;
import com.tricon.ticketmanagementsystem.service.IAdminService;
import com.tricon.ticketmanagementsystem.vo.*;


@Service
public class AdminService implements IAdminService{
	
	@Autowired
	private AdminDao adminDao; 
	
	public List<Employee> getAllEmployees() {
		
		System.out.println("Inside Service");
		return adminDao.getAllEmployees();
	}
	
	public List<Employee> getEmployee(int id){
		System.out.println("Inside Service");
		return adminDao.getEmployee(id);
	}
	
	
	public List<Group> getGroups(int page ,int total) {
		
		System.out.println("Inside Service");
		return adminDao.getGroups(page ,total);
	}
	
	public String addGroup(Group obj){
		
		System.out.println("Inside Service");
		return adminDao.addGroup(obj);
		
	}
	
	public Group getGroup(int id) {
		
		System.out.println("Inside Service");
		return adminDao.getGroup(id);
	
	}
	
	public String updateGroup(Group obj , int id)
	{
		System.out.println("Inside Service");
		return adminDao.updateGroup(obj , id);
	}
	
	public String  deleteGroup(int id) 
	{
		System.out.println("Inside Service");
		return adminDao.deleteGroup(id);
	}
	
	public int countGroups() {
	
		System.out.println("Inside Service");
		 return adminDao.countGroups();
	}
}
