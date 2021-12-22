package com.aelion.mycrm.dto;

public class LightPerson {
	private Long id;
	private String lastName;
	private String firstName;
	private String occupation;
	private String company;
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Long getId() {
		return id;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getOccupation() {
		return occupation;
	}
	public String getCompany() {
		return company;
	}
	
	
}
