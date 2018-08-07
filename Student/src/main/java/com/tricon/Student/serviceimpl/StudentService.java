package com.tricon.Student.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricon.Student.dao.IStudentDao;
import com.tricon.Student.model.Student;
import com.tricon.Student.service.IStudentService;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private IStudentDao studentDao; 
	
	@Override
	public List<Student> getName() {
		
		return studentDao.getName();
	}
	
    public List<Student> getNamebyId(int id) {
		
		return studentDao.getNamebyId(id);
	}
    
   public String addStudent(Student stu) {
		
		return studentDao.addStudent(stu);
	}
   
   public String deleteStudent(int id) {
	   System.out.println("Inside: delete service");
		
		return studentDao.deleteStudent(id);
		
	}

   public String  updateStudent(Student stu) {
	   
	   return studentDao.updateStudent(stu);
	   
   }
}
