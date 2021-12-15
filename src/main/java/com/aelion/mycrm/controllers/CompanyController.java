package com.aelion.mycrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.mycrm.models.Company;
import com.aelion.mycrm.services.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService service;
	
	@PostMapping()
	@CrossOrigin
	public ResponseEntity<Company> create(@RequestBody Company company) {
		return new ResponseEntity<Company>(
				this.service.add(company),
				HttpStatus.CREATED
		);
				
	}
}
