package com.tricon.ticketmanagementsystem.vo;

import java.util.List;

public class Ticket {
	
	private int ticketId;
	private String subject;
	private ModelTicketType ticket_type;
	private Group group;
	private Comment comment;
	private Employee assignee;
	private Employee assigner;
	private List<Employee> watcher; 
	private Priority priority;
	private String description;
	private Status status;
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public ModelTicketType getTicket_type() {
		return ticket_type;
	}
	public void setTicket_type(ModelTicketType ticket_type) {
		this.ticket_type = ticket_type;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Employee getAssignee() {
		return assignee;
	}
	public void setAssignee(Employee assignee) {
		this.assignee = assignee;
	}
	public Employee getAssigner() {
		return assigner;
	}
	public void setAssigner(Employee assigner) {
		this.assigner = assigner;
	}
	public List<Employee> getWatcher() {
		return watcher;
	}
	public void setWatcher(List<Employee> watcher) {
		this.watcher = watcher;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
