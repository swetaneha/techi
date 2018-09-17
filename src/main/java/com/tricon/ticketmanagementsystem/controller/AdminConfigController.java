package com.tricon.ticketmanagementsystem.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tricon.ticketmanagementsystem.serviceimpl.AdminService;
import com.tricon.ticketmanagementsystem.vo.*;


@CrossOrigin("*")
@RestController
public class AdminConfigController {
		    
		@Autowired
		private AdminService adminService;
		
		@RequestMapping(value="/employee", method=RequestMethod.GET)
		@ResponseBody
		public List<Employee> getEmployees(){
			System.out.println("Inside: Controller employees");
			return adminService.getAllEmployees();
		}
		
		
		@RequestMapping(value="/employee/{id}", method=RequestMethod.GET)
		@ResponseBody
		public List<Employee> getEmployee(@PathVariable int id){
			System.out.println("Inside: Controller employees");
			return adminService.getEmployee(id);
		}
	
		@RequestMapping(value="/group", method=RequestMethod.GET)
		@ResponseBody
		public List<Group> getGroups(){
			System.out.println("Inside: Controller getgroups");
			return adminService.getGroups();
		}
		
		@RequestMapping(value="/group/create", method=RequestMethod.POST)
		@ResponseBody
		public void addGroup(@RequestBody Group obj){
			System.out.println("Inside: Controller postgroups");
			adminService.addGroup(obj);
		}
		
		@RequestMapping(value="/group/{id}", method=RequestMethod.GET)
		@ResponseBody
		public Group getGroup(@PathVariable int id) {
			System.out.println("Inside: Controller");
			return adminService.getGroup(id);
		}
		
		@RequestMapping(value="/group/update/{id}", method=RequestMethod.PUT)
		@ResponseBody
		public void updateGroup(@PathVariable int id , @RequestBody Group obj){
			System.out.println("Inside: Controller");
			adminService.updateGroup(obj , id);
		
		}
		@RequestMapping(value="/group/delete/{id}", method=RequestMethod.PUT)
		@ResponseBody
		public void deleteGroup(@PathVariable int id){
			System.out.println("Inside: Controller");
			adminService.deleteGroup(id);
		
		}
}

