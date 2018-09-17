package com.tricon.ticketmanagementsystem.daoimpl;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.tricon.ticketmanagementsystem.vo.Status;
import com.tricon.ticketmanagementsystem.vo.Ticket;

@Repository
public class TicketManagementSystemDao implements ITicketManagementSystemDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String FETCH_EMP_NAME= "SELECT empId,fName,mName,lName FROM ticketmanagementdb.employee where userName=?";
	public static final String DELETE_STATUS="UPDATE `ticketmanagementdb`.`ticket_status` SET `isArchived` = '1' WHERE (`id` = ?)";
	public static final String FETCH_STATUS="SELECT * FROM `ticketmanagementdb`.`ticket_status` ORDER BY updatedOn DESC";
	public static final String INSERT_INTO_STATUS="INSERT INTO `ticketmanagementdb`.`ticket_status` (`status`, `isArchived`, `createdOn`, `updatedOn`) VALUES (?,'0',now(),now())";
	public static final String MYSQL_CHECK_FOR_PRESENCE_OF_STATUS_DETAILS="SELECT COUNT(*) FROM `ticketmanagementdb`.`ticket_status` where status=?";
	public static final String UPDATE_STATUS="UPDATE `ticketmanagementdb`.`ticket_status` SET `status` = ? , `isArchived` = '0', `updatedOn` = now() WHERE (`id` = ?)";
	public static final String UPDATE_STATUS_In="UPDATE `ticketmanagementdb`.`ticket_status` SET `status` = ? , `isArchived` = '1', `updatedOn` = now() WHERE (`id` = ?)";
	public static final  String ADD_WATCHER_TICKET="INSERT INTO `ticketmanagementdb`.`watcher` (`employeeId`, `ticketId`, `isArchived`) VALUES (?,?,?)";
	public static final  String ADD_TICKET="INSERT INTO `ticketmanagementdb`.`ticket` (`employeeId`, `assignee`, `description`, `groupId`, `ticketType`, `isArchived`) VALUES (?, ?, ?, ?, ?,?)";
	public static final  String ADD_COMMENT="INSERT INTO `ticketmanagementdb`.`comment` (`comment`, `updatedBy`, `updatedOn`, `createdBy`, `createdOn`, `isArchived`) VALUES (?,?,now(),?,now(),'0')";;
	public static final  String UPDATE_TICKET="UPDATE `ticketmanagementdb`.`ticket` SET `employeeId` = ?, `assignee` = ?, `groupId` = ?, `ticketType` = ?, `isArchived` = ? WHERE (`id` = ?)";
	public static final  String FETCH_COMMENT="SELECT * FROM `ticketmanagementdb`.`comment` ORDER BY updatedOn DESC";
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
		String s =status.getIsArchived();
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
	 	public List<Status> getStatus() { 
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
	public String addTicket(Ticket ticket) {
		// TODO Auto-generated method 
		
		int ticket_id=ticket.getTicketId();
		//Add ticket in ticket table
		Employee assignee = ticket.getAssignee();
		Employee assigner = ticket.getAssigner();
		Group group = ticket.getGroup();
		ModelTicketType ticketType=ticket.getTicket_type();
//		Comment comment = ticket
        jdbcTemplate.update(ADD_TICKET, assigner.getEmpId() ,assignee.getEmpId(),ticket.getDescription(),group.getGroupId(),ticketType.getId(),0); 
		
		//Add watcher
		
		
		List<Employee>  watcher= ticket.getWatcher();
        
        List<Object[]> data_of_watcher_ticket_ref = new ArrayList<Object[]>();
        
//        String size[] = new String[watcher.size()];

        
        for(Employee watch: watcher){
        	
            Object[] tmp = {  watch.getEmpId(),ticket_id, 0};
            System.out.println(watch.getEmpId());
            data_of_watcher_ticket_ref.add(tmp);
        	}
            jdbcTemplate.batchUpdate(ADD_WATCHER_TICKET, data_of_watcher_ticket_ref);
          
       // add subject
            String comment_ticket=(ticket.getComment()).getComment();
            jdbcTemplate.update(ADD_COMMENT, comment_ticket ,assignee.getEmpId(),assignee.getEmpId());
		
		return null;
	} 

	
	}
