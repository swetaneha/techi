
package com.tricon.ticketmanagementsystem.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tricon.ticketmanagementsystem.dao.IAdminWorkflowDao;
import com.tricon.ticketmanagementsystem.vo.StatusForWorkflow;
import com.tricon.ticketmanagementsystem.vo.Workflow;
import com.tricon.ticketmanagementsystem.vo.WorkflowBatch;
import com.tricon.ticketmanagementsystem.vo.WorkflowCreate;
import com.tricon.ticketmanagementsystem.vo.WorkflowStatusRef;




@Repository
public class AdminWorkflowDao implements IAdminWorkflowDao {

	private static final String FETCH_WORKFLOWS = "SELECT id, workflow, workflow_description,isArchived FROM workflow";
	private static final String FETCH_STATUSES = "SELECT id,status from ticket_status WHERE isArchived=false";
	private static final String FETCH_OLD_STATUSID = "SELECT statusId FROM workflow_status_ref WHERE workflowId='";
	private static final String FETCH_OLD_RANKS = "SELECT rankOfStatus FROM workflow_status_ref WHERE workflowId='";
	private static final String UPDATE_STATUS_ID = "UPDATE workflow_status_ref SET statusId=?  WHERE workflowId=? and rankOfStatus=?";
	private static final String UPDATE_RANK = "UPDATE workflow_status_ref SET rankOfStatus=?  WHERE workflowId=? and statusId=?";
	private static final String CREATE_WORKFLOW = "INSERT INTO workflow (workflow, workflow_description) VALUES (?,?)";
	private static final String CREATE_WORKFLOW_RANK = "INSERT INTO workflow_status_ref(workflowId, statusId, rankOfStatus) VALUES (?,?,?)";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<StatusForWorkflow> displayStatuses() {

		return this.jdbcTemplate.query(FETCH_STATUSES,
				new RowMapper<StatusForWorkflow>() {
					public StatusForWorkflow mapRow(ResultSet rs, int rowNum) throws SQLException {
						StatusForWorkflow s = new StatusForWorkflow();
						s.setStatusId(rs.getInt("id"));
						s.setStatus(rs.getString("status"));
						return s;
					}
				});

	}

	@Override
	public String setStatusesToWorkflow(WorkflowStatusRef obj) {
	
		String FETCH_OLD_STATUS = FETCH_OLD_STATUSID + obj.getWorkflowId()+"'";
		List<Integer> oldStatusIdWorkflow = jdbcTemplate.queryForList(FETCH_OLD_STATUS, Integer.class);
		for (int j = 0; j < oldStatusIdWorkflow.size(); j++) {
			System.out.println(oldStatusIdWorkflow.get(j));
		}

		String FETCH_OLD_RANK = FETCH_OLD_RANKS + obj.getWorkflowId()+"'";
		List<Integer> oldRanks = jdbcTemplate.queryForList(FETCH_OLD_RANK, Integer.class);
		for (int j = 0; j < oldRanks.size(); j++) {
			System.out.println(oldRanks.get(j));
		}

		String statusIdWorkflow = obj.getstatusIdWorkflow();
		String[] statusStrings = statusIdWorkflow.split(" ");
		int[] array_of_statuses = new int[statusStrings.length];
		for (int i = 0; i < statusStrings.length; i++) {
			array_of_statuses[i] = Integer.parseInt(statusStrings[i]);
		}

		String rank = obj.getRank();

		System.out.println("Array of ranks");
		String[] rankStrings = rank.split(" ");
		int[] array_of_ranks = new int[rankStrings.length];
		for (int i = 0; i < rankStrings.length; i++) {
			array_of_ranks[i] = Integer.parseInt(rankStrings[i]);
			System.out.println(array_of_ranks[i]);
		}

		List<WorkflowBatch> data_of_status_workflow_ref = new ArrayList<WorkflowBatch>();

		int id = obj.getWorkflowId();

		for (int i = 0; i < oldStatusIdWorkflow.size(); i++) {
			WorkflowBatch tmp = new WorkflowBatch();
			tmp.setBatchStatusId(array_of_statuses[i]);
			tmp.setBatchRank(array_of_ranks[i]);
			tmp.setBatchWorkflowId(id);
			tmp.setBatchOldStatusId(oldStatusIdWorkflow.get(i));
			tmp.setBatchOldRank(oldRanks.get(i));
			data_of_status_workflow_ref.add(tmp);
		}

		jdbcTemplate.batchUpdate(UPDATE_STATUS_ID,
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {

						WorkflowBatch smallBatch = data_of_status_workflow_ref.get(i);
						ps.setInt(1, smallBatch.getBatchStatusId());
						ps.setInt(2, smallBatch.getBatchWorkflowId());
						ps.setInt(3, smallBatch.getBatchOldRank());
					}

					@Override
					public int getBatchSize() {
						return data_of_status_workflow_ref.size();
					}
				});

		jdbcTemplate.batchUpdate(UPDATE_RANK,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {

						WorkflowBatch smallBatch = data_of_status_workflow_ref.get(i);
						ps.setInt(1, smallBatch.getBatchRank());
						ps.setInt(2, smallBatch.getBatchWorkflowId());
						ps.setInt(3, smallBatch.getBatchStatusId());
					}

					@Override
					public int getBatchSize() {

						return data_of_status_workflow_ref.size();
					}
				});
		return "SUCCESS";
	}

	public List<Workflow> displayWorkflows() {
		return this.jdbcTemplate.query(FETCH_WORKFLOWS, new RowMapper<Workflow>() {
			public Workflow mapRow(ResultSet rs, int rowNum) throws SQLException {
				Workflow s = new Workflow();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("workflow"));
				s.setDescription(rs.getString("workflow_description"));
				boolean archiveStatus=(rs.getBoolean("isArchived"));
				if(archiveStatus)
					s.setIsActive("No");
				else
					s.setIsActive("Yes");
				return s;
			}
		});
	}

	public String createWorkflow(WorkflowCreate obj) {

		jdbcTemplate.update(CREATE_WORKFLOW,obj.getName(),obj.getDescription());
		
		String FETCH_WORKFLOWID = "SELECT Id FROM workflow WHERE workflow='" + obj.getName()+ "'"+" and workflow_description='"+obj.getDescription()+"'";
		int workflowId = jdbcTemplate.queryForObject(FETCH_WORKFLOWID, Integer.class);

		int[] array_of_statuses; 
		array_of_statuses = obj.getStatus();       
    	
		int[] array_of_ranks;
        array_of_ranks = obj.getRanks();
     
    	
    	List<WorkflowBatch> data_of_status_workflow_ref = new ArrayList<WorkflowBatch>();
    	
    	for (int i = 0; i < array_of_ranks.length; i++) {
			WorkflowBatch tmp = new WorkflowBatch();
			tmp.setBatchStatusId(array_of_statuses[i]);
			tmp.setBatchRank(array_of_ranks[i]);
			tmp.setBatchWorkflowId(workflowId);
			data_of_status_workflow_ref.add(tmp);
		}
    	
    	jdbcTemplate.batchUpdate(CREATE_WORKFLOW_RANK,
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {

						WorkflowBatch smallBatch = data_of_status_workflow_ref.get(i);
						ps.setInt(1, smallBatch.getBatchWorkflowId());
						ps.setInt(2, smallBatch.getBatchStatusId());
						ps.setInt(3, smallBatch.getBatchRank());
					}

					@Override
					public int getBatchSize() {
						return data_of_status_workflow_ref.size();
					}
				});
    	return "SUCCESS";
	}

}
