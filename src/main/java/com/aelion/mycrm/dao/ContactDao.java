package com.aelion.mycrm.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.aelion.mycrm.models.Occupation;
import com.aelion.mycrm.models.Person;
import com.aelion.mycrm.repositories.PersonRepository;
public class ContactDao {
	private PersonRepository personRepository;
	
	public ContactDao(PersonRepository repository) {
		this.personRepository = repository;
	}
	
	public List<Person> getPersons(int count, int offset) {
		List<Person> persons = (List<Person>) this.personRepository.findAll();
		return persons
			.stream()
			.skip(offset)
			.limit(count)
			.collect(Collectors.toList());
	}
	
	public Occupation getOccupation(Person person) {
		return this.personRepository.findById(person.getId()).get().getOccupation();
	}
}
