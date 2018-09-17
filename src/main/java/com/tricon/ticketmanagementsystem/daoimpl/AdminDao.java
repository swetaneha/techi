package com.tricon.ticketmanagementsystem.daoimpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tricon.ticketmanagementsystem.dao.IAdminDao;
import com.tricon.ticketmanagementsystem.vo.*;

@Repository
public class AdminDao implements IAdminDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String FETCH_EMPLOYEES="SELECT empId,fName,mName,lName,emailId,isArchived from employee e WHERE e.isArchived= 0";
	
	private static final String FETCH_EMPLOYEE="SELECT empId,fName,mName,lName,emailId,isArchived from employee e WHERE e.empId= ?";
	
	private static final String FETCH_GROUPS="SELECT DISTINCT ga.adminId,g.id,e.fName,e.mName,e.lName,g.name,gd.description,g.isArchived FROM ticketmanagementdb.group g inner join ticketmanagementdb.group_admin ga on g.id=ga.groupId inner join ticketmanagementdb.group_description gd on g.id=gd.groupId inner join ticketmanagementdb.employee_group_ref egr on egr.groupId=g.id inner join ticketmanagementdb.employee e on ga.adminId = e.empId ORDER BY g.updatedOn DESC";
	
	private static final String FETCH_GROUP="SELECT DISTINCT ga.adminId,egr.empId,g.id,e.fName,e.mName,e.lName,g.name,gd.description,g.isArchived,e.isArchived FROM ticketmanagementdb.group g inner join ticketmanagementdb.group_admin ga on g.id=ga.groupId inner join ticketmanagementdb.group_description gd on g.id=gd.groupId inner join ticketmanagementdb.employee_group_ref egr on egr.groupId=g.id inner join ticketmanagementdb.employee e on egr.empId = e.empId WHERE g.id=? and egr.isArchived = 0";
	
	private static final String ADD_GROUP_NAME ="INSERT INTO `ticketmanagementdb`.`group` (`name`, `createdOn`, `createdBy`, `updatedOn`, `updatedBy`, `isArchived`) VALUES (?,NOW(),?,NOW(),?,?)";
	private static final String ADD_GROUP_ADMIN ="INSERT INTO `ticketmanagementdb`.`group_admin` (`groupId`, `adminId`, `isArchived`) VALUES (?,?,?)";
	private static final String ADD_GROUP_EMPLOYEES ="INSERT INTO `ticketmanagementdb`.`employee_group_ref` (`groupId`, `empId`, `isArchived`) VALUES (?,?,?)";	
	private static final String ADD_GROUP_DESCRIPTION = "INSERT INTO `ticketmanagementdb`.`group_description` (`groupId`, `description`,`isArchived`) VALUES (?,?,?)";
	
	private static final String DELETE_GROUP = "UPDATE ticketmanagementdb.group SET isArchived=1 , updatedOn=now() , updatedBy=2177  WHERE id=?";
	private static final String DELETE_GROUP_DESCRIPTION = "UPDATE ticketmanagementdb.group_description SET isArchived=1 WHERE groupId=?";
	private static final String DELETE_GROUP_ADMIN = "UPDATE ticketmanagementdb.group_admin SET isArchived=1 WHERE groupId=?";
	private static final String DELETE_EMPLOYEE_GROUP_REFERENCE = "UPDATE ticketmanagementdb.employee_group_ref SET isArchived=1 WHERE groupId=?";
	
	private static final String UPDATE_GROUP_NAME = "UPDATE `ticketmanagementdb`.`group` SET `name` = ? , `updatedOn` = NOW() , `updatedBy` = ? , `isArchived` = ? WHERE (`id` = ?)";
	private static final String UPDATE_GROUP_ADMIN = "UPDATE `ticketmanagementdb`.`group_admin` SET `adminId` = ? , `isArchived` = ?  WHERE (`groupId` = ?)";
	private static final String UPDATE_GROUP_DESCRIPTION = "UPDATE `ticketmanagementdb`.`group_description` SET `description` = ? , `isArchived` = ?  WHERE (`groupId` = ? )";
	private static final String UPDATE_GROUP_MEMBERS = "UPDATE `ticketmanagementdb`.`employee_group_ref` SET `isArchived` = ? WHERE `groupId` = ? AND empId= ?";

	
	private String groupStatus= "";
	private String employeeStatus= "";
	private Group group;
	private int superAdminId = 2177; 

	public static boolean compare(String str1) {
	    if(str1 == null) {
	    	return false;
	    }
	    else {
	    	return true;
	    }
	}
	

	@Override
	public List<Employee> getAllEmployees() {
		System.out.println("Inside Dao");
		return this.jdbcTemplate.query(FETCH_EMPLOYEES, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee s=new Employee();
			
			s.setfName(rs.getString("e.fName"));
			s.setmName(rs.getString("e.mName"));
			s.setlName(rs.getString("e.lName"));
			

			if(compare(rs.getString("e.mName"))) {
				
				if(compare(rs.getString("e.lName"))) {
					
					s.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName")+" "+rs.getString("e.lName"));
				} 
				else {
					s.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName"));
				}
			}
			else {
				if(compare(rs.getString("e.lName"))) {
					
					s.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.lName"));
				}
				else {
					
					s.setEmpName(rs.getString("e.fName"));
				}
			}
			
	
			s.setEmailId(rs.getString("emailId"));
			s.setEmpId(rs.getInt("empId"));
			
			employeeStatus= (rs.getString("e.isArchived"));
			
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
	public List<Employee> getEmployee(int id) {
		System.out.println("Inside Dao");
		return this.jdbcTemplate.query(FETCH_EMPLOYEE, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				
			Employee s=new Employee();
			
			s.setfName(rs.getString("e.fName"));
			s.setmName(rs.getString("e.mName"));
			s.setlName(rs.getString("e.lName"));
			

			if(compare(rs.getString("e.mName"))) {
				
				if(compare(rs.getString("e.lName"))) {
					
					s.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName")+" "+rs.getString("e.lName"));
				} 
				else {
					s.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName"));
				}
			}
			else {
				if(compare(rs.getString("e.lName"))) {
					
					s.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.lName"));
				}
				else {
					
					s.setEmpName(rs.getString("e.fName"));
				}
			}
			
	
			s.setEmailId(rs.getString("emailId"));
			s.setEmpId(rs.getInt("empId"));
			
			employeeStatus= (rs.getString("e.isArchived"));
			
			if(employeeStatus.compareTo("0")== 0)
			{
				s.setIsArchived("Active");
			}
			else {
				s.setIsArchived("Inactive");
			}
			
			return s;
			}
		},id);	
	}	
	

	@Override
	public List<Group> getGroups() {
		
		System.out.println("Inside Dao");
		return this.jdbcTemplate.query(FETCH_GROUPS, new RowMapper<Group>() {
			public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Employee employee = new Employee();
				
				employee.setfName(rs.getString("e.fName"));
				employee.setmName(rs.getString("e.mName"));
				employee.setlName(rs.getString("e.lName"));
				
				if(compare(rs.getString("e.mName"))) {
					
					if(compare(rs.getString("e.lName"))) {
						
						employee.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName")+" "+rs.getString("e.lName"));
					} 
					else {
						
						employee.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName"));
					}
				}
				else {
					if(compare(rs.getString("e.lName"))) {
						
						employee.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.lName"));
					}
					else {
						
						employee.setEmpName(rs.getString("e.fName"));
					}
				}
				
				employee.setEmpId(rs.getInt("ga.adminId"));
				
				
			group=new Group(employee);	
			group.setGroupId(rs.getInt("g.id"));
			group.setGroupName(rs.getString("g.name"));
			group.setDescription(rs.getString("gd.description"));
			groupStatus= (rs.getString("g.isArchived"));
			
			
			if(groupStatus.compareTo("0")== 0)
			{
				group.setIsArchived("Active");
			}
			else {
				group.setIsArchived("Inactive");
			}
			
			
			return group;
			}
		});	
	}
	
	@Override
	public Group getGroup(int id) {
		
		System.out.println("Inside getOneGroup  ");
		List<Employee> groupmembers= new ArrayList<>() ;	
		Employee admin = new Employee();
		System.out.println("Inside Dao");
		 List <Group> groplist = this.jdbcTemplate.query(FETCH_GROUP, new RowMapper<Group>() {
			public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
						
			if(rs.getInt("ga.adminId") == rs.getInt("egr.empId")) {
				
				admin.setfName(rs.getString("e.fName"));
				admin.setmName(rs.getString("e.mName"));
				admin.setlName(rs.getString("e.lName"));
				
				if(compare(rs.getString("e.mName"))) {
					
					if(compare(rs.getString("e.lName"))) {
						
						admin.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName")+" "+rs.getString("e.lName"));
					} 
					else {
						
						admin.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName"));
					}
				}
				else {
					if(compare(rs.getString("e.lName"))) {
						
						admin.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.lName"));
					}
					else {
						
						admin.setEmpName(rs.getString("e.fName"));
					}
				}
				
				admin.setEmpId(rs.getInt("ga.adminId"));
						
			}
			
			Employee member = new Employee();
			
			member.setfName(rs.getString("e.fName"));
			member.setmName(rs.getString("e.mName"));
			member.setlName(rs.getString("e.lName"));
			
			if(compare(rs.getString("e.mName"))) {
				
				if(compare(rs.getString("e.lName"))) {
					
					member.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName")+" "+rs.getString("e.lName"));
				} 
				else {
					
					member.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName"));
				}
			}
			else {
				if(compare(rs.getString("e.lName"))) {
					
					member.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.lName"));
				}
				else {
					
					member.setEmpName(rs.getString("e.fName"));
				}
				
			}
			
			
			member.setEmpId(rs.getInt("egr.empId"));
			groupmembers.add(member);
			
		
		group=new Group(admin , groupmembers );	
		
		group.setGroupId(rs.getInt("g.id"));
		group.setGroupName(rs.getString("g.name"));
		group.setDescription(rs.getString("gd.description"));
			
					
			group.setIsArchived(rs.getString("g.isArchived"));
	
			
			return group;
	}
			
   },id);
		 
		 return group;
	}
	
	
	@Override
	public void addGroup(Group obj) {
		
		System.out.println("Inside Dao");
		int status=0;
			String isArchived= obj.getIsArchived();
			
			if(isArchived.compareTo("0")== 0) {
				status=0;
	            }
				else {
				status=1;
			}
						
			jdbcTemplate.update(ADD_GROUP_NAME, obj.getGroupName() , superAdminId , superAdminId , status);

		    String FETCH_GROUP_ID = "SELECT id FROM ticketmanagementdb.`group` where name='"+ obj.getGroupName() + "'";
		    int group_id = jdbcTemplate.queryForObject(FETCH_GROUP_ID, int.class);
		     
	        jdbcTemplate.update(ADD_GROUP_DESCRIPTION, group_id, obj.getDescription(), status); 
	        
	        Employee admin = obj.getGroupadmin();
	        jdbcTemplate.update(ADD_GROUP_ADMIN, group_id, admin.getEmpId() , 0); 
	        

	        List<Employee> groupmembers = obj.getGroupmembers();
	        	        
	        List<Object[]> data_of_employee_group_ref = new ArrayList<Object[]>();
	        
	        for(Employee emp: groupmembers){
	        	
	            Object[] tmp = { group_id, emp.getEmpId(), 0};
	            data_of_employee_group_ref.add(tmp);
				
				
	     }
	        jdbcTemplate.batchUpdate(ADD_GROUP_EMPLOYEES, data_of_employee_group_ref); 
	        	        
	    }
	
	@Override
	public void updateGroup(Group obj , int id) {
		
			System.out.println("Inside Dao");
			int status=0;
				String isArchived= obj.getIsArchived();
				
				if(isArchived.compareTo("0")== 0) {
					status=0;
		            }
					else {
					status=1;
			}
				
			jdbcTemplate.update(UPDATE_GROUP_NAME , obj.getGroupName() , superAdminId , status , id );
			
			Employee employee = obj.getGroupadmin();
			jdbcTemplate.update(UPDATE_GROUP_ADMIN , employee.getEmpId() , status ,id );
			
			jdbcTemplate.update(UPDATE_GROUP_DESCRIPTION , obj.getDescription() , status ,id );
			
			 List<Employee> groupmembers = obj.getGroupmembers();
			
			String employees_in_groups = "SELECT empId FROM ticketmanagementdb.employee_group_ref egr WHERE groupId= ? "; 
			
			List <int[]> grouplist = this.jdbcTemplate.query(employees_in_groups, new RowMapper<int[]>() {
				public int[] mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					int a[] = new int [1];
					a[0] = rs.getInt("egr.empId");
					
					return a;
				}
		      }, id);
			
			 List<Object[]> data_of_employee_group_ref = new ArrayList<Object[]>();
			 String insert_new_members_in_group = "INSERT INTO `ticketmanagementdb`.`employee_group_ref` (`groupId`, `empId`, `isArchived`) VALUES (?,?,?)";		        	        
			 String inactive_all_existing_member ="UPDATE `ticketmanagementdb`.`employee_group_ref` SET `isArchived` = 1 WHERE `groupId` = ?";
			 jdbcTemplate.update(inactive_all_existing_member , id );
			 
			 boolean check_for_existing_employee = false;
			 

			for (Employee c : groupmembers) {
				for (int[] b : grouplist) {
					
					if(c.getEmpId() == b[0]) {
						check_for_existing_employee = true;
						break;
					}
				}
				
				
				if(check_for_existing_employee) {
					
					Object[] tmp = { 0 , id , c.getEmpId() };
		            data_of_employee_group_ref.add(tmp);
					
				}
				else {
						
						jdbcTemplate.update( insert_new_members_in_group , id , c.getEmpId() , 0 );
						Object[] tmp = { 0 , id , c.getEmpId() };
			            data_of_employee_group_ref.add(tmp);
				}
				check_for_existing_employee =false;
			}
			
			jdbcTemplate.batchUpdate(UPDATE_GROUP_MEMBERS, data_of_employee_group_ref); 
		} 	        
	
	@Override
	public void  deleteGroup(int id) {
			
		jdbcTemplate.update(DELETE_GROUP , id);
		jdbcTemplate.update(DELETE_GROUP_DESCRIPTION , id);
		jdbcTemplate.update(DELETE_GROUP_ADMIN , id);  
		jdbcTemplate.update(DELETE_EMPLOYEE_GROUP_REFERENCE, id);
		}
	}

