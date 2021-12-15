package com.aelion.mycrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.mycrm.models.Company;
import com.aelion.mycrm.repositories.CompanyRepository;


@Service
public class CompanyService {
	@Autowired
	private CompanyRepository repository;
	
	public Company add(Company company) {
		return this.repository.save(company);
	}
}
