package com.tricon.ticketmanagementsystem.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
		@RequestMapping(value="/group",params = { "page","total"}, method=RequestMethod.GET)
		@ResponseBody
		public List<Group> getGroups(@RequestParam( "page" ) int page, @RequestParam( "total" ) int total ){
			System.out.println("Inside: Controller getgroups");
			 if(page==1){
			       page = 0;
		        }  
		        else{  
		            page=((page-1)*total);  
		        }  
			return adminService.getGroups(page,total);
		}
		
		@RequestMapping(value="/group/create", method=RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<String> addGroup(@RequestBody Group obj){
			System.out.println("Inside: Controller postgroups");
			String responseStatus = adminService.addGroup(obj);
			
			if(responseStatus.equals( "SUCCESS" ))
			    return new ResponseEntity<String>("{\"Success\":\"true\" , \"Messsage\": \"Group Created Successfully\" }", HttpStatus.OK);
			else
			{
			return new ResponseEntity<String>("{\"Success\":\"false\" , \"Messsage\": \"Group name already Exists\" }", HttpStatus.OK);
			}
		}
		
		@RequestMapping(value="/group/{id}", method=RequestMethod.GET)
		@ResponseBody
		public Group getGroup(@PathVariable int id) {
			System.out.println("Inside: Controller");
			return adminService.getGroup(id);
		}
		
		@RequestMapping(value="/group/update/{id}", method=RequestMethod.PUT)
		@ResponseBody
		public ResponseEntity<String> updateGroup(@PathVariable int id , @RequestBody Group obj){
			
			System.out.println("Inside: Controller");
			String responseStatus = adminService.updateGroup(obj , id);
			
			if(responseStatus.equals( "SUCCESS" ))
			    return new ResponseEntity<String>("{\"Success\":\"true\" , \"Messsage\": \"Group Updated Successfully\"}", HttpStatus.OK);
			else
			{
			return new ResponseEntity<String>("{\"Success\":\"false\" , \"Messsage\": \"Updated Group Already Exists\"}", HttpStatus.OK);
			}
		
		
		}
		
		@RequestMapping(value="/group/delete/{id}", method=RequestMethod.PUT)
		@ResponseBody
		public ResponseEntity<String> deleteGroup(@PathVariable int id){
			
			System.out.println("Inside: Controller");
			String responseStatus = adminService.deleteGroup(id);
			
			if(responseStatus.equals( "SUCCESS" ))
			    return new ResponseEntity<String>("{\"Success\":\"true\" ,  \"Messsage\": \"Group Deleted Successfully\"}", HttpStatus.OK);
			else
			{
				return new ResponseEntity<String>("{\"Success\":\"false\" , \"Messsage\": \"Group is Not Active\" }", HttpStatus.OK);
			
			}
		
		}
		
		@RequestMapping(value="/group/count", method=RequestMethod.GET)
		public int countGroups(){
			System.out.println("Inside: Controller");
			return adminService.countGroups();
		
		}
}

