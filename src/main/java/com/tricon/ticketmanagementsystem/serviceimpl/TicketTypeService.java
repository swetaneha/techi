package com.tricon.ticketmanagementsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tricon.ticketmanagementsystem.daoimpl.*;
import com.tricon.ticketmanagementsystem.service.*;
import com.tricon.ticketmanagementsystem.vo.*;


@Service
public class TicketTypeService implements ITicketTypeService{
	
	@Autowired
	TicketTypeDao ticketTypeDao;
	
	@Override
	public List<ModelTicketType> retrieveAllTicketType(int page, int total) {
		// TODO Auto-generated method stub
		return ticketTypeDao.retrieveAllTicketType(page, total);
	}

	@Override
	public void  updateTicketType(ModelTicketType modelTicketType) {
		// TODO Auto-generated method stub
		ticketTypeDao.updateTicketType(modelTicketType);
	}

	@Override
	public void addTicketType(ModelTicketType modelTicketType) {
		// TODO Auto-generated method stub
		ticketTypeDao.addTicketType(modelTicketType);
	}
	
	
	
	

}
