package com.tricon.Student.dao;

import java.util.List;

import com.tricon.Student.model.Student;

public interface IStudentDao {
	public List<Student> getName();
	public List<Student> getNamebyId(int id);
	public String  addStudent(Student stu);
	public String  deleteStudent(int id);
	public String  updateStudent(Student stu);
	
}
