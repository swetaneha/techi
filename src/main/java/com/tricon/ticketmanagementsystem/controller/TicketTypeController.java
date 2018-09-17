package com.tricon.ticketmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.tricon.ticketmanagementsystem.serviceimpl.*;
import com.tricon.ticketmanagementsystem.vo.*;

@CrossOrigin(origins="*")
@RestController
public class TicketTypeController {
	
	
	
	@Autowired
	private TicketTypeService ticketTypeService;

	

	@RequestMapping(value = "/tickettype",params = { "page", "total"}, method = RequestMethod.GET)
	List<ModelTicketType> retreiveAllPageable(@RequestParam( "page" ) int page, @RequestParam( "total" ) int total ){
		 
        if(page==1){
        	
        }  
        else{  
            page=((page-1)*total)+1;  
        }  
		return  ticketTypeService.retrieveAllTicketType(page,total);

	}

	
	
	@PostMapping("/tickettype")
	public String addTicketType(@RequestBody ModelTicketType modelTicketType) {
		Gson gson = new Gson();
		ticketTypeService.addTicketType(modelTicketType);
		return gson.toJson("Success");
	}
	
	
	@PutMapping("/tickettype")
		public String updateTicketType(@RequestBody ModelTicketType modelTicketType) {
		Gson gson = new Gson();
		ticketTypeService.updateTicketType(modelTicketType);
		return gson.toJson("Success");
		
	}
	
	
	

}
