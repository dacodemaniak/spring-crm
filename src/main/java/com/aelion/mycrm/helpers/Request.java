package com.aelion.mycrm.helpers;

import java.util.List;

public class Request {
	private String userName;
	private String userPass;
	private List<String> roles;
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPass() {
		return this.userPass;
	}
	
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public List<String> getRoles() {
		return this.roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
