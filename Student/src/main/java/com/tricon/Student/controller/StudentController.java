package com.tricon.Student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tricon.Student.model.Student;
import com.tricon.Student.service.IStudentService;

@RestController
public class StudentController {

	@Autowired
	private IStudentService  student;
	
	@RequestMapping("/health")
	public List<Student> ping() {
		System.out.println("Inside: Controller");
		return student.getName();
	}
	
	
	
	@RequestMapping(value="/health", method=RequestMethod.POST)
	public String addStudent(@RequestBody Student stu) {
		System.out.println("Inside: Controller");
		return student.addStudent(stu);
	}
	
	@RequestMapping(value="/health/{id}", method=RequestMethod.DELETE)
	public String deleteStudent(@PathVariable int id) {
		System.out.println("Inside: delete Controller");
		return student.deleteStudent(id);
	}
	
	@RequestMapping(value="/health/{id}",method=RequestMethod.GET)  
	public List<Student> ping1(@PathVariable int id) {
		System.out.println("Inside: Controller");
		return student.getNamebyId(id);
	}
	
	@RequestMapping(value="/health", method=RequestMethod.PUT)
	public String updateStudent(@RequestBody Student stu) {
		System.out.println("Inside: Controller");
		return student.updateStudent(stu);
	}
}
