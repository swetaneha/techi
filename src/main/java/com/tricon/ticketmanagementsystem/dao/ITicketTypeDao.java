package com.tricon.ticketmanagementsystem.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tricon.ticketmanagementsystem.vo.*;

public interface ITicketTypeDao {
	
	public List<ModelTicketType> retrieveAllTicketType(int page, int total);
	public void updateTicketType(ModelTicketType modelTicketType);
	public void addTicketType(ModelTicketType modelTicketType);


}
