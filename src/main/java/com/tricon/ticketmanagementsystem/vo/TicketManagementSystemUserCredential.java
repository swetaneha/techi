package com.tricon.ticketmanagementsystem.vo;

public class TicketManagementSystemUserCredential {

	private String uid;
	private String userName;
	private String password;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUsername(String sAMAccountName) {
		this.userName = sAMAccountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
