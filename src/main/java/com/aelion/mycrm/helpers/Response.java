package com.aelion.mycrm.helpers;

import java.util.List;

public class Response {
	private String token;
	
	private List<String> roles;
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public List<String> getRoles() {
		return this.roles;
	}
}
