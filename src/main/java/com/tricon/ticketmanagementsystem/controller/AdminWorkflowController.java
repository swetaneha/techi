package com.tricon.ticketmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tricon.ticketmanagementsystem.service.IAdminWorkflowService;
import com.tricon.ticketmanagementsystem.vo.StatusForWorkflow;
import com.tricon.ticketmanagementsystem.vo.Workflow;
import com.tricon.ticketmanagementsystem.vo.WorkflowCreate;
import com.tricon.ticketmanagementsystem.vo.WorkflowStatusRef;




@CrossOrigin("*")
@RestController
public class AdminWorkflowController {
	
	@Autowired
	private IAdminWorkflowService adminWorkflowService;
	

	@RequestMapping(value="/statuses", method=RequestMethod.GET)
	@ResponseBody
	public List<StatusForWorkflow> getStatuses(){
		System.out.println("Inside: Controller");
		return adminWorkflowService.displayStatuses();
	}

	

//	@RequestMapping(value="/statusRank", method=RequestMethod.PUT)
//	@ResponseBody
//	public ResponseEntity<String> updateStatusAndRanks(@RequestBody WorkflowStatusRef obj){
//		System.out.println("Inside: Controller");
//		String responseStatus=adminWorkflowService.setStatusesToWorkflow(obj);
//		if(responseStatus=="SUCCESS")
//		    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
//		else
//		{
//			return new ResponseEntity<String>("RECORD ALREADY EXISTS", HttpStatus.UNAUTHORIZED);
//		}
//	}
	
	
	@PutMapping("/statusRank")
	public String updateStatusAndRanks(@RequestBody WorkflowStatusRef obj) {
	Gson gson = new Gson();
	adminWorkflowService.setStatusesToWorkflow(obj);
	return gson.toJson("Success");
	
    }
	

	@RequestMapping(value="/workflows", method=RequestMethod.GET)
	@ResponseBody
	public List<Workflow> getWorkflows(){
		System.out.println("Inside: Controller");
		return adminWorkflowService.displayWorkflows();
	}
	

//	@RequestMapping(value="/workflow", method=RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity<String> createAWorkflow(@RequestBody WorkflowCreate obj){
//		System.out.println("Inside: Controller");
//		String responseStatus=adminWorkflowService.createWorkflow(obj);
//		if(responseStatus=="SUCCESS")
//		    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
//		else
//		{
//			return new ResponseEntity<String>("RECORD ALREADY EXISTS", HttpStatus.UNAUTHORIZED);
//		}
//	}
	
	@PostMapping("/workflow")
	public String createAWorkflow(@RequestBody WorkflowCreate obj) {
		Gson gson = new Gson();
		adminWorkflowService.createWorkflow(obj);
		return gson.toJson("Success");
	}
	
}
