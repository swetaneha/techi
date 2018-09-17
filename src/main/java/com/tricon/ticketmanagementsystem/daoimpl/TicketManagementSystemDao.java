package com.tricon.ticketmanagementsystem.daoimpl;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tricon.ticketmanagementsystem.dao.ITicketManagementSystemDao;
import com.tricon.ticketmanagementsystem.vo.Comment;
import com.tricon.ticketmanagementsystem.vo.Employee;
import com.tricon.ticketmanagementsystem.vo.Group;
import com.tricon.ticketmanagementsystem.vo.ModelTicketType;
import com.tricon.ticketmanagementsystem.vo.Priority;
import com.tricon.ticketmanagementsystem.vo.Status;
import com.tricon.ticketmanagementsystem.vo.Ticket;

@Repository
public class TicketManagementSystemDao implements ITicketManagementSystemDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String FETCH_EMP_NAME= "SELECT empId,fName,mName,lName FROM ticketmanagementdb.employee where userName=?";
	public static final String DELETE_STATUS="UPDATE `ticketmanagementdb`.`ticket_status` SET `isArchived` = '1' WHERE (`id` = ?)";
	public static final String FETCH_STATUS="SELECT * FROM `ticketmanagementdb`.`ticket_status` WHERE `isArchived` = '0' ";
	public static final String INSERT_INTO_STATUS="INSERT INTO `ticketmanagementdb`.`ticket_status` (`status`, `isArchived`, `createdOn`, `updatedOn`) VALUES (?,'0',now(),now())";
	public static final String MYSQL_CHECK_FOR_PRESENCE_OF_STATUS_DETAILS="SELECT COUNT(*) FROM `ticketmanagementdb`.`ticket_status` where status=?";
	public static final String UPDATE_STATUS="UPDATE `ticketmanagementdb`.`ticket_status` SET `status` = ? , `isArchived` = '0', `updatedOn` = now() WHERE (`id` = ?)";
	public static final String UPDATE_STATUS_In="UPDATE `ticketmanagementdb`.`ticket_status` SET `status` = ? , `isArchived` = '1', `updatedOn` = now() WHERE (`id` = ?)";
	public static final String CHECK_STATUS_IN_WORKFLOW="SELECT COUNT(*) FROM `ticketmanagementdb`.`workflow_status_ref` where statusId=?";
	public static final String CHECK_WATCHER="SELECT COUNT(*) FROM `ticketmanagementdb`.`watcher` where ticketId=? AND employeeId=?";
	public static final String CHECK_COMMENT="SELECT COUNT(*) FROM `ticketmanagementdb`.`comment` where ticketId=? AND employeeId=?";
	public static final  String ADD_WATCHER_TICKET="INSERT INTO `ticketmanagementdb`.`watcher` (`employeeId`, `ticketId`, `isArchived`) VALUES (?,?,'0')"; 
	public static final  String ADD_TICKET="INSERT INTO `ticketmanagementdb`.`ticket` (`employeeId`, `assignee`, `description`, `groupId`, `ticketType`, `isArchived`) VALUES (?, ?, ?, ?, ?,?)"; 
	public static final  String ADD_COMMENT="INSERT INTO `ticketmanagementdb`.`comment` (`comment`, `updatedBy`, `updatedOn`, `createdBy`, `createdOn`, `isArchived`) VALUES (?,?,now(),?,now(),'0')";
	public static final  String UPDATE_TICKET="UPDATE `ticketmanagementdb`.`ticket` SET `employeeId` = ?, `assignee` = ?, `groupId` = ?, `ticketType` = ?, `isArchived` = ? WHERE (`id` = ?)"; 
	public static final  String FETCH_COMMENT="SELECT * FROM `ticketmanagementdb`.`comment` ORDER BY updatedOn DESC";
	public static final  String FETCH_PRIORITY="SELECT * FROM `ticketmanagementdb`.`priority`";
	public static final  String ADD_PRIORITY="INSERT INTO `ticketmanagementdb`.`priority_ref` (`ticketId`, `priorityId`, `createdBy`, `createdOn`, `updatedBy`, `updatedOn`, `isArchived`) VALUES (?, ?, ?, now(),?, now(), '0')";
	public static final  String ADD_STATUS="INSERT INTO `ticketmanagementdb`.`ticket_status_ref` (`ticketId`, `statusId`, `createdOn`, `createdBy`, `updatedOn`, `updatedBy`, `isArchived`) VALUES (?, ?,now(), ?,now(), ?, '0')";
	public static final  String CHECK_STATUS="SELECT COUNT(*) FROM `ticketmanagementdb`.`ticket_status_ref` where ticketId=? AND statusId=? AND `isArchived` = '0' ";
	public static final  String SET_STATUS="UPDATE `ticketmanagementdb`.`ticket_status_ref` SET `isArchived` = '1' where ticketId=? AND `isArchived` = '0' ";
	public static final  String SET_WATCHER="UPDATE `ticketmanagementdb`.`watcher` SET `isArchived` = '0' WHERE ticketId=? AND employeeId=? AND `isArchived` = '1' "; 
	public static final  String CHECK_PRIORITY="SELECT COUNT(*) FROM `ticketmanagementdb`.`priority_ref` where ticketId=? AND priorityId=? AND `isArchived` = '0' ";
	public static final  String SET_PRIORITY="UPDATE `ticketmanagementdb`.`priority_ref` SET `isArchived` = '1' where ticketId=? AND `isArchived` = '0' ";

	@Override
	public Employee getName(String userName) {
		
	return this.jdbcTemplate.queryForObject(FETCH_EMP_NAME, new RowMapper<Employee>() {
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employeeDetails = new Employee();
			employeeDetails.setEmpId(rs.getInt("empid"));
			employeeDetails.setfName(rs.getString("fName"));
			employeeDetails.setmName(rs.getString("mName"));
			employeeDetails.setlName(rs.getString("lName"));
			return employeeDetails;
			}
		},userName);
	}
	
	@Override
	public String  addStatus(Status status) {
		int re;
		String stat;
		System.out.println("dao");
		stat=status.getStatus();
		System.out.println(stat);
		//stat="in development";
		re= jdbcTemplate.queryForObject(MYSQL_CHECK_FOR_PRESENCE_OF_STATUS_DETAILS,new Object[] { stat },Integer.class);
		System.out.println(re);
		if(re==0)
		{
		jdbcTemplate.update(INSERT_INTO_STATUS,status.getStatus());
		return "SUCCESS";
		}
		else
		{
			return "Record already exist";
		}
		
	}

	@Override
	public String updateStatus(Status status) {
		int workflow;
		String s =status.getIsArchived();
		System.out.println(status.getId());
		String stat;
		stat=Integer.toString(status.getId());
		workflow=jdbcTemplate.queryForObject(CHECK_STATUS_IN_WORKFLOW,new Object[] { stat },Integer.class);
		System.out.println(workflow);
		System.out.println(s);
		if(workflow>0)
		{
			System.out.println("Status in use");

			return "Status in use";
		}
		
		if(s.compareTo("Active")== 0) {
		     jdbcTemplate.update(UPDATE_STATUS,status.getStatus(),status.getId());}
		else {
		     jdbcTemplate.update(UPDATE_STATUS_In,status.getStatus(),status.getId());
		}
		return "SUCCESS";
	}
	
	@Override
	public String deleteStatus(Status status) {
		
		jdbcTemplate.update(DELETE_STATUS,status.getId());
		return "SUCCESS";
	}
	
	@Override 
	 	public List<Status> getStatus(int page,int total) { 
		
		    String FETCH_STATUS="SELECT * FROM `ticketmanagementdb`.`ticket_status` ORDER BY updatedOn DESC limit "+(page-1)+","+total;
	  		return this.jdbcTemplate.query(FETCH_STATUS, new RowMapper<Status>() { 
	  			public Status mapRow(ResultSet rs, int rowNum) throws SQLException { 
	  			Status s=new Status(); 
	  			s.setId(rs.getInt("id")); 
	  			s.setStatus(rs.getString("status"));
	  			s.setIsArchived(rs.getString("IsArchived"));
	  			String employeeStatus= (rs.getString("isArchived"));
				
				if(employeeStatus.compareTo("0")== 0)
				{
					s.setIsArchived("Active");
				}
				else {
					s.setIsArchived("Inactive");
				}
	  			return s; 
	  			} 
	  		}); 
	  	}
	
	public int getCount() {
	String COUNT="select count(*) from ticket_status";
	return jdbcTemplate.queryForObject(COUNT,Integer.class);
	}
	
	@Override  
	   	public List<Comment> getComment(){ 
	  		 
	  		return this.jdbcTemplate.query(FETCH_COMMENT, new RowMapper<Comment>() {  
	    			public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {  
	    			Comment s=new Comment();  
	    			s.setId(rs.getInt("id"));  
	    			s.setComment(rs.getString("comment")); 
	    			s.setIsArchived(rs.getString("IsArchived")); 
	    			String employeeStatus= (rs.getString("isArchived")); 
	  			 
	  			if(employeeStatus.compareTo("0")== 0) 
	  			{ 
	  				s.setIsArchived("Active"); 
	  			} 
	  			else { 
	  				s.setIsArchived("Inactive"); 
	  			} 
	    			return s;  
	    			}  
	    		});  
	  		 
	  	} 
	
	@Override  
   	public List<Priority> getPriority(){ 
  		 
  		return this.jdbcTemplate.query(FETCH_PRIORITY, new RowMapper<Priority>() {  
    			public Priority mapRow(ResultSet rs, int rowNum) throws SQLException {  
    			Priority s=new Priority();  
    			s.setId(rs.getInt("id"));  
    			s.setPriority(rs.getString("priority")); 
    			s.setIsArchived(rs.getString("IsArchived")); 
    			String priority= (rs.getString("isArchived")); 
    			return s;
  			   }  
    		});  
  		 
  	} 
	  
	 
	  	@Override 
	  	public String updateTicket(Ticket ticket) {
	  		 
  		 int ticket_id=ticket.getTicketId(); 
  		 
  		 //update ticket in ticket table 
  		 Employee assignee = ticket.getAssignee(); 
  		 Employee assigner = ticket.getAssigner(); 
 		 Group group = ticket.getGroup(); 
  		 ModelTicketType ticketType=ticket.getTicket_type(); 
  	     jdbcTemplate.update(UPDATE_TICKET, assigner.getEmpId() ,assignee.getEmpId(),group.getGroupId(),ticketType.getId(),0,ticket_id);  
  		 
	 	 //Add watcher 
  		 List<Employee>  watcher= ticket.getWatcher(); 
          
         for(Employee watch: watcher){  
             int count=jdbcTemplate.queryForObject(CHECK_WATCHER,new Object[] { ticket_id,watch.getEmpId()},Integer.class);
             System.out.println(watch.getEmpId());
             System.out.println(count); 
             if(count==0)
                  jdbcTemplate.update(ADD_WATCHER_TICKET, watch.getEmpId(),ticket_id);
             
             jdbcTemplate.update(SET_WATCHER, ticket_id,watch.getEmpId());
             
            	 
              
          	} 
          
          //Add priority
          Priority priority=ticket.getPriority();
          int count_priority=jdbcTemplate.queryForObject(CHECK_PRIORITY,new Object[] { ticket_id,priority.getId()},Integer.class);
          if(count_priority==0)
          {
        	  
          jdbcTemplate.update(SET_PRIORITY,ticket_id);	  
          jdbcTemplate.update(ADD_PRIORITY,ticket_id, priority.getId(), assignee.getEmpId(), assignee.getEmpId());
         
          
          }
          
          //Add status
          Status status=ticket.getStatus();
          int count_status=jdbcTemplate.queryForObject(CHECK_STATUS,new Object[] { ticket_id,status.getId()},Integer.class);
          if(count_status==0) {
        	 
            jdbcTemplate.update(SET_STATUS,ticket_id);
            jdbcTemplate.update(ADD_STATUS,ticket_id, status.getId(), assignee.getEmpId(), assignee.getEmpId());
            
          }
          
	  	  return null; 
	  	  
	  	  //
  	}  
   
	  	
	  	@Override 
	  	public List<Status> getStatusWithOutPagination() { 
	  	  return this.jdbcTemplate.query(FETCH_STATUS, new RowMapper<Status>() { 
	  	  public Status mapRow(ResultSet rs, int rowNum) throws SQLException { 
	  	  Status s=new Status(); 
	  	  s.setId(rs.getInt("id")); 
	  	  s.setStatus(rs.getString("status"));
	  	  s.setIsArchived(rs.getString("IsArchived"));
	  	  String employeeStatus= (rs.getString("isArchived"));
	  	  if(employeeStatus.compareTo("0")== 0)
	  	  {
	  	      s.setIsArchived("Active");
	  	  }
	  	else {
	  	s.setIsArchived("Inactive");
	  	}
	  	  return s; 
	  	  
	  	  } 
	  	  
	  	  }); 
	  	 
	  	} 
	  	
	  	
	}
