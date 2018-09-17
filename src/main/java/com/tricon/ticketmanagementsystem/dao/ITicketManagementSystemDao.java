package com.tricon.ticketmanagementsystem.dao;


import java.util.List;

import com.tricon.ticketmanagementsystem.vo.Comment;
import com.tricon.ticketmanagementsystem.vo.Employee;
import com.tricon.ticketmanagementsystem.vo.Priority;
import com.tricon.ticketmanagementsystem.vo.Status;
import com.tricon.ticketmanagementsystem.vo.Ticket;

public interface ITicketManagementSystemDao {

	public Employee getName(String userName);
	public String  addStatus(Status status);
	public String  updateStatus(Status status);
	public String  deleteStatus(Status status);
	public List<Status>  getStatus(int page,int total);
	public int getCount();
	public String updateTicket(Ticket ticket);
	public List<Comment> getComment();
	public List<Priority> getPriority();
	public List<Status> getStatusWithOutPagination();
}
