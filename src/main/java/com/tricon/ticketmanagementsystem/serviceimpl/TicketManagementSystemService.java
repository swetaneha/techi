package com.tricon.ticketmanagementsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricon.ticketmanagementsystem.dao.ITicketManagementSystemDao;
import com.tricon.ticketmanagementsystem.service.ITicketManagementSystemService;
import com.tricon.ticketmanagementsystem.vo.Comment;
import com.tricon.ticketmanagementsystem.vo.Employee;
import com.tricon.ticketmanagementsystem.vo.Status;
import com.tricon.ticketmanagementsystem.vo.Ticket;

@Service
public class TicketManagementSystemService implements ITicketManagementSystemService {

	@Autowired
	private ITicketManagementSystemDao TicketManagementSystemDao; 
	
	@Override
	public Employee getName(String userName) {
		
		return TicketManagementSystemDao.getName(userName);
	}
	
//	@Autowired
//	private ITicketManagementSystemDao TicketManagementSystemDao;

	@Override
	public String addStatus(Status status) {
		System.out.println("service");
		return TicketManagementSystemDao.addStatus(status);
	}
	
	@Override
	public String updateStatus(Status status) {
		System.out.println("service");
		return TicketManagementSystemDao.updateStatus(status);
	}
	
	@Override
	public String deleteStatus(Status status) {
		System.out.println("service");
		return TicketManagementSystemDao.deleteStatus(status);
	}
 
	@Override 
	  	public List<Status>  getStatus() { 
	  		System.out.println("service"); 
	  		return TicketManagementSystemDao.getStatus(); 
	  	} 
	
	@Override 
  	public List<Comment>  getComment() { 
  		System.out.println("service"); 
  		return TicketManagementSystemDao.getComment(); 
  	} 
	
	@Override 
	public String addTicket(Ticket ticket) { 
  		System.out.println("service"); 
  		return TicketManagementSystemDao.addTicket(ticket); 
  	} 

}
