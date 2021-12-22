package com.aelion.mycrm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.mycrm.models.Person;
import com.aelion.mycrm.repositories.PersonRepository;

import com.aelion.mycrm.dto.LightPerson;

@Service
public class ContactService {
	@Autowired
	PersonRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public Person save(Person person) {
		return this.repository.save(person);
	}
	
	public List<LightPerson> all() {
		List<Person> persons = (List<Person>) this.repository.findAll();
		
		return persons.stream().map(person -> mapper.map(person, LightPerson.class))
		.collect(Collectors.toList());
	}
}
