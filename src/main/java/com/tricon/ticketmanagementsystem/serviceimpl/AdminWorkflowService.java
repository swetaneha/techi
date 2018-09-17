package com.tricon.ticketmanagementsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricon.ticketmanagementsystem.dao.IAdminWorkflowDao;
import com.tricon.ticketmanagementsystem.service.IAdminWorkflowService;
import com.tricon.ticketmanagementsystem.vo.StatusForWorkflow;
import com.tricon.ticketmanagementsystem.vo.Workflow;
import com.tricon.ticketmanagementsystem.vo.WorkflowCreate;
import com.tricon.ticketmanagementsystem.vo.WorkflowStatusRef;





@Service
public class AdminWorkflowService implements IAdminWorkflowService {
	
	@Autowired
	private IAdminWorkflowDao adminWorkflowDao;
	
	public List<StatusForWorkflow> displayStatuses(){
			
		return adminWorkflowDao.displayStatuses();

	}

	@Override
	public List<Workflow> displayWorkflows() {
		// TODO Auto-generated method stub
		return adminWorkflowDao.displayWorkflows();
	}

	@Override
	public String createWorkflow(WorkflowCreate obj) {
		// TODO Auto-generated method stub
		return adminWorkflowDao.createWorkflow(obj);
	}

	@Override
	public String setStatusesToWorkflow(WorkflowStatusRef obj) {
		return adminWorkflowDao.setStatusesToWorkflow(obj);
		
	}
}
