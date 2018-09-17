package com.tricon.ticketmanagementsystem.ldap;

//import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import com.tricon.ticketmanagementsystem.vo.UserInfo;

public class TicketManagementSystemRepo {

	@Autowired
	private LdapTemplate ldapTemplate;

	public ResponseEntity<UserInfo> auth (String sAMAccountName, String password) {
		
		boolean value=false;
		UserInfo userInfo=new UserInfo();
		try {
			AndFilter filter= new AndFilter();
			ldapTemplate.setIgnorePartialResultException(true);
			    filter.and(new EqualsFilter("sAMAccountName",sAMAccountName));
			   
			value=ldapTemplate.authenticate("",filter.toString(), password);
		}catch(Exception e) {
			
			System.out.println("login failed"+e.getMessage());
			e.printStackTrace();
		}
		System.out.println(value);
		if(value)
		{   userInfo.setStatusid(1);
			userInfo.setUsername(sAMAccountName);
			return new ResponseEntity<>(userInfo, HttpStatus.OK);
		}
		else
		{
			userInfo.setStatusid(0);
			userInfo.setUsername(sAMAccountName);
			return new ResponseEntity<>(userInfo, HttpStatus.UNAUTHORIZED);
		}
	}
}
