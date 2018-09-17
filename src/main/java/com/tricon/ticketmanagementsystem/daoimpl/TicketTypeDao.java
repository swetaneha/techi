package com.tricon.ticketmanagementsystem.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tricon.ticketmanagementsystem.dao.*;
import com.tricon.ticketmanagementsystem.vo.*;


@Repository 
public class TicketTypeDao implements ITicketTypeDao {
	
	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	 private static final String ADD_TICKET_TYPE ="INSERT INTO `ticketmanagementdb`.`ticket_type` (`description`, `ticketType`, `workflow`, `isArchiveId`,`createdOn`,`updatedOn`) VALUES (?,?,?,?,now(),now())"; 
	 private static final String UPDATE_TICKET_TYPE ="UPDATE ticketmanagementdb.ticket_type SET description = ?,ticketType =?,workflow=?,isArchiveId=?,updatedOn=now() where id=?";
			 

	
	@Override
	public List<ModelTicketType> retrieveAllTicketType(int page ,int total) {
		
		String FETCH_TICKET="SELECT id,description,ticketType,workflow,isArchiveId from ticket_type order by updatedOn DESC"; 
		return this.jdbcTemplate.query(FETCH_TICKET, new RowMapper<ModelTicketType>() {
			public ModelTicketType mapRow(ResultSet rs, int rowNum) throws SQLException {
			ModelTicketType modelTicketType=new ModelTicketType();
			modelTicketType.setId(rs.getInt("id"));
			
			modelTicketType.setTicketType(rs.getString("ticketType"));
			modelTicketType.setDescription(rs.getString("description"));
		    modelTicketType.setWorkFlow(rs.getString("workflow"));
			boolean archiveStatus=rs.getBoolean("isArchiveId");
			if(archiveStatus)
					modelTicketType.setArchiveId("InActive");
			else
				    modelTicketType.setArchiveId("Active");
			return modelTicketType;
			}
			}); 
	}

	@Override
	public void updateTicketType(ModelTicketType modelTicketType) {
		int boolString;
		if (modelTicketType.getArchiveId().compareTo("Active")==0)
			boolString=0;
		else
			boolString=1;
		jdbcTemplate.update(UPDATE_TICKET_TYPE,modelTicketType.getDescription(),modelTicketType.getTicketType(),modelTicketType.getWorkFlow(),boolString, modelTicketType.getId());
		
		
	}

	@Override
	public void addTicketType(ModelTicketType modelTicketType) {
		int boolString;
		if (modelTicketType.getArchiveId().compareTo("Active")==0)
			boolString=0;
		else
			boolString=1;
		jdbcTemplate.update(ADD_TICKET_TYPE, modelTicketType.getDescription(),modelTicketType.getTicketType(),modelTicketType.getWorkFlow(),boolString);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
