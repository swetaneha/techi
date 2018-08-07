package com.tricon.Student.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tricon.Student.dao.IStudentDao;
import com.tricon.Student.model.Student;

@Repository
public class StudentDao implements IStudentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String FETCH_STUDENT="select * from records";

	
	@Override
	public List<Student> getName() {
		return this.jdbcTemplate.query(FETCH_STUDENT, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student s=new Student();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setAddress(rs.getString("address"));
			return s;
			}
		});
	}
	
//private static final String FETCH_STUDENT_By_Id="select * from records where id = "+;

	
	@Override
	public List<Student> getNamebyId(int id) {
		
		return this.jdbcTemplate.query("select * from records where id = "+id, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student s=new Student();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setAddress(rs.getString("address"));
			return s;
			}
		});
	}

    public String  addStudent(Student stu) {
    	jdbcTemplate.execute("insert into records(id,name,address) values('"+stu.getId()+"','"+stu.getName()+"','"+stu.getAddress()+"')");
    	return "SUCCESS";
    }
    
    public String  deleteStudent(int sid) {
    	System.out.println("Inside: delete dao");
    	jdbcTemplate.execute("delete from records where id="+sid);
    	return "DELETED";
    }
    
    public String  updateStudent(Student stu) {
    	System.out.println("Inside: update dao");
    	jdbcTemplate.execute("UPDATE records SET name = '"+stu.getName()+"' WHERE id = "+ stu.getId());
    	return "UPDATED";
    }

}
