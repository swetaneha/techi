package com.tricon.ticketmanagementsystem.service;

import java.util.List;

import com.tricon.ticketmanagementsystem.vo.StatusForWorkflow;
import com.tricon.ticketmanagementsystem.vo.Workflow;
import com.tricon.ticketmanagementsystem.vo.WorkflowCreate;
import com.tricon.ticketmanagementsystem.vo.WorkflowStatusRef;




public interface IAdminWorkflowService {
	
	public List<StatusForWorkflow> displayStatuses();
	public String setStatusesToWorkflow(WorkflowStatusRef obj);
	public List<Workflow> displayWorkflows();
	public String createWorkflow(WorkflowCreate obj);

}
