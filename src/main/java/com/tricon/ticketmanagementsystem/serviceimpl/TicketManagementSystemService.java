package com.tricon.ticketmanagementsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricon.ticketmanagementsystem.dao.ITicketManagementSystemDao;
import com.tricon.ticketmanagementsystem.service.ITicketManagementSystemService;
import com.tricon.ticketmanagementsystem.vo.Comment;
import com.tricon.ticketmanagementsystem.vo.Employee;
import com.tricon.ticketmanagementsystem.vo.Priority;
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
	  	public List<Status>  getStatus(int page,int total) { 
	  		System.out.println("service"); 
	  		return TicketManagementSystemDao.getStatus(page,total); 
	  	} 
	
	@Override 
  	public List<Priority>  getPriority() { 
  		System.out.println("service"); 
  		return TicketManagementSystemDao.getPriority(); 
  	} 
	
	@Override
	public int getCount()
	{
		return TicketManagementSystemDao.getCount();
	}
	
	@Override
	public String updateTicket(Ticket ticket) {
		return TicketManagementSystemDao.updateTicket(ticket);
	}
	
	@Override
	public List<Comment> getComment() {
		return TicketManagementSystemDao.getComment();
	}
	
	@Override 
  	public List<Status>  getStatusWithOutPagination() { 
  		System.out.println("service"); 
  		return TicketManagementSystemDao.getStatusWithOutPagination(); 
  	} 

}
