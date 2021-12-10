package com.aelion.mycrm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.mycrm.models.Person;

@RestController
@RequestMapping("/person")
public class PersonController {

	@GetMapping()
	@CrossOrigin
	public List<String> getDummy() {
		// Your logic here !
		ArrayList<String> persons = new ArrayList<String>();
		persons.add("Aubert");
		persons.add("Casper");
		
		return persons;
	}
	
	@GetMapping("/all")
	@CrossOrigin
	public List<Person> getPersons() {
		ArrayList<Person> persons = new ArrayList<Person>();
		
		persons.add(new Person("Aubert", "Jean-Luc"));
		persons.add(new Person("Latte", "Truddy"));
		
		return persons;
	}
}
