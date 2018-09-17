package com.tricon.ticketmanagementsystem.service;

import java.util.List;

import com.tricon.ticketmanagementsystem.vo.Comment;
import com.tricon.ticketmanagementsystem.vo.Employee;
import com.tricon.ticketmanagementsystem.vo.Status;
import com.tricon.ticketmanagementsystem.vo.Ticket;

public interface ITicketManagementSystemService {

	public Employee getName(String userName);
	public String  addStatus(Status status);
	public String  updateStatus(Status status);
	public String  deleteStatus(Status status);
	public List<Status>  getStatus();
	public String addTicket(Ticket ticket);
	public List<Comment> getComment();
}
