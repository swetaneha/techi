package com.tricon.ticketmanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tricon.ticketmanagementsystem.vo.*;


public interface ITicketTypeService {
	
	
    public List<ModelTicketType> retrieveAllTicketType(int pageId, int total);
	public void updateTicketType(ModelTicketType modelTicketType);
	public void addTicketType(ModelTicketType modelTicketType);

}
