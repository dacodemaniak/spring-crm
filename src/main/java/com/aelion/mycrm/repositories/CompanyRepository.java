package com.aelion.mycrm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aelion.mycrm.models.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

}
