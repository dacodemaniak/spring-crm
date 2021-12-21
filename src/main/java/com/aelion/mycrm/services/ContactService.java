package com.aelion.mycrm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.mycrm.models.Person;
import com.aelion.mycrm.repositories.PersonRepository;

@Service
public class ContactService {
	@Autowired
	PersonRepository repository;
	
	public Person save(Person person) {
		return this.repository.save(person);
	}
	
	public List<Person> all() {
		return (List<Person>) this.repository.findAll();
	}
}
